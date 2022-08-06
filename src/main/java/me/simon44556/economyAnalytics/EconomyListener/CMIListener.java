package me.simon44556.economyAnalytics.EconomyListener;

import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataHandler.DataHandler;

public class CMIListener {
    EconomyAnalytics plugin;
    DataHandler _handler;

    public CMIListener(EconomyAnalytics plugin) {
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager().getPlugin("CMI") == null) {
            plugin.getLogger().log(Level.SEVERE, "CMI not provided");
            return;
        }
        try {
            this._handler = new DataHandler();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCMIEconomyEvent(CMIUserBalanceChangeEvent e) {

    }
}
