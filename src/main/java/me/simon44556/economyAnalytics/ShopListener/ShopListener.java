package me.simon44556.economyAnalytics.ShopListener;

import org.bukkit.entity.Player;
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

        Player p = result.getPlayer();
        String mat = result.getShopItem().getItem().getType().name();

        if (mat == null) {
            mat = "Unknown";
        }

        ShopAction action = result.getShopAction();

        double amount = result.getAmount();

        ShopEvent test = new ShopEvent(currentTimeMillis(), p.getUniqueId().toString(), matchEventType(action), amount,
                mat);

        plugin.getPlanProvider().storeTransaction(test);

        
        System.out.println("TEST");
        System.out.println(e.getResult().getAmount());
        System.out.println(e.getResult().getPrice());
        System.out.println(e.getResult().getResult().toString());
        System.out.println(e.getResult().getPlayer().getDisplayName());

        System.out.println(e.getResult().getShopItem().getId());
        System.out.println(e.getResult().getShopItem().getItem().getType().toString());
        System.out.println(e.getResult().getShopItem().getItem().getType().name());

        System.out.println(e.getResult().getShopAction());
        System.out.println("TEST END");

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

    public int currentTimeMillis() {
        return (int) (System.currentTimeMillis() & 0x00000000FFFFFFFFL);
    }

}