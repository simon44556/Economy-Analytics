package me.simon44556.economyAnalytics.EconomyListener;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataHandler.DataHandler;
import me.simon44556.economyAnalytics.EconomyEvents.Economy.PlayerDepositEvent;
import me.simon44556.economyAnalytics.EconomyEvents.Economy.PlayerWithdrawEvent;

public class EconomyListener implements Listener {
    EconomyAnalytics plugin;
    DataHandler _handler;

    public EconomyListener(EconomyAnalytics plugin) {
        this.plugin = plugin;
        try {
            this._handler = new DataHandler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEconomyDeposit(PlayerWithdrawEvent e) {
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEconomyWithdraw(PlayerDepositEvent e) {
    }
}
