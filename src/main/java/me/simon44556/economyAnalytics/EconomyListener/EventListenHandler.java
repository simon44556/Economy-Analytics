package me.simon44556.economyAnalytics.EconomyListener;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataHandler.DataHandler;
import me.simon44556.economyAnalytics.DataTypes.BalanceEvent;
import me.simon44556.economyAnalytics.DataTypes.EconomyEvent;
import me.simon44556.economyAnalytics.DataTypes.ShopEvent;
import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;
import net.brcdev.shopgui.shop.Shop;
import net.brcdev.shopgui.shop.ShopManager.ShopAction;

public abstract class EventListenHandler implements Listener {
    protected EconomyAnalytics plugin;
    protected DataHandler _handler;

    public EventListenHandler(EconomyAnalytics plugin) {
        this.plugin = plugin;
        try {
            this._handler = new DataHandler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTransaction(EconomyEvent dataForDB) {
        if (dataForDB instanceof ShopEvent) {
            System.out.println("Log as Shop");
            Bukkit.getScheduler().runTaskAsynchronously(plugin,
                    () -> _handler.storeTransaction((ShopEvent) dataForDB));

        } else {
            System.out.println("Log as Balance");
            Bukkit.getScheduler().runTaskAsynchronously(plugin,
                    () -> _handler.storeTransaction((BalanceEvent) dataForDB));
        }

    }

    public void saveTransaction(ShopEvent dataForDB) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> _handler.storeTransaction(dataForDB));
    }

    /*
     * CMI Actions
     * setBalance, Withdraw, Deposit
     */
    public EventType matchEventType(String action) {
        switch (action) {
            case "setBalance":
                return EventType.SET_BALANCE;
            case "Withdraw":
                return EventType.WITHDRAW;
            case "Deposit":
                return EventType.DEPOSIT;
            default:
                return EventType.OTHER;
        }
    }

    public EventType matchEventType(ShopAction action) {
        switch (action) {
            case BUY:
                return EventType.BUY;
            case SELL:
                return EventType.SELL;
            case SELL_ALL:
                return EventType.SELL_ALL;
            default:
                return EventType.OTHER;
        }
    }
}
