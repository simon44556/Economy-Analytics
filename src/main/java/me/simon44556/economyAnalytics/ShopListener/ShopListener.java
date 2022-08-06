package me.simon44556.economyAnalytics.ShopListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataTypes.ShopEvent;
import me.simon44556.economyAnalytics.DataTypes.Enums.ShopEventType;
import net.brcdev.shopgui.event.ShopPostTransactionEvent;
import net.brcdev.shopgui.shop.ShopManager.ShopAction;
import net.brcdev.shopgui.shop.ShopTransactionResult;
import net.brcdev.shopgui.shop.ShopTransactionResult.ShopTransactionResultType;

public class ShopListener implements Listener {
    EconomyAnalytics plugin;

    public ShopListener(EconomyAnalytics plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onShopTransaction(ShopPostTransactionEvent e) {
        ShopTransactionResult result = e.getResult();
        if (result == null) {
            return;
        }

        ShopTransactionResultType transactionResult = result.getResult();
        if (transactionResult != ShopTransactionResultType.SUCCESS) {
            return;
        }

        String uuid = result.getPlayer().getUniqueId().toString();
        String mat = result.getShopItem().getItem().getType().name();
        if (mat == null) {
            mat = "Unknown";
        }

        ShopAction action = result.getShopAction();
        int amount = result.getAmount();
        double price = result.getPrice();

        ShopEvent dataForDB = new ShopEvent(System.currentTimeMillis(), uuid, matchEventType(action), amount, price,
                mat);

        /*Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.getPlanProvider().storeTransaction(dataForDB);
        });*/
    }

    public ShopEventType matchEventType(ShopAction action) {
        switch (action) {
            case BUY:
                return ShopEventType.BUY;
            case SELL:
                return ShopEventType.SELL;
            case SELL_ALL:
                return ShopEventType.SELL_ALL;
            default:
                return ShopEventType.OTHER;
        }
    }

}