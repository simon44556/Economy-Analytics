package me.simon44556.economyAnalytics.EconomyListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataTypes.BalanceEvent;

public class CMIListener extends EventListenHandler {
    public CMIListener(EconomyAnalytics plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCMIEconomyEvent(CMIUserBalanceChangeEvent e) {
        System.out.println("CMI Event for " + e.getSource().getName() + " to " + e.getUser().getName() + " "
                + e.getFrom() + " " + e.getTo() + " " + e.getActionType());
        saveTransaction(new BalanceEvent(System.currentTimeMillis(), e.getSource().getUniqueId().toString(),
                matchEventType(e.getActionType()), e.getFrom() - e.getTo()));
    }
}
