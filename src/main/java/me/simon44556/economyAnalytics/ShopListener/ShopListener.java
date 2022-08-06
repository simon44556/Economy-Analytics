package me.simon44556.economyAnalytics.ShopListener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataTypes.ShopEvent;
import me.simon44556.economyAnalytics.EconomyListener.EventListenHandler;
import net.brcdev.shopgui.event.ShopPostTransactionEvent;
import net.brcdev.shopgui.shop.ShopManager.ShopAction;
import net.brcdev.shopgui.shop.ShopTransactionResult;
import net.brcdev.shopgui.shop.ShopTransactionResult.ShopTransactionResultType;

public class ShopListener extends EventListenHandler {
    public ShopListener(EconomyAnalytics plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
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

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> _handler.storeTransaction(dataForDB));
    }

}