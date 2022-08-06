package me.simon44556.economyAnalytics.EconomyListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataTypes.BalanceEvent;
import me.simon44556.economyAnalytics.DataTypes.Enums.EventType;

public class CMIListener extends EventListenHandler {
    public CMIListener(EconomyAnalytics plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST) // Highest priority in case it gets canceled before
    public void onCMIEconomyEvent(CMIUserBalanceChangeEvent e) {
        String uuid = e.getSource() != null ? e.getSource().getUniqueId().toString()
                : e.getUser().getUniqueId().toString();

        double priceDiff = e.getFrom() - e.getTo();

        EventType eventType = matchEventType(e.getActionType());

        if (eventType == EventType.SET_BALANCE) {
            if (priceDiff > 0) {
                eventType = EventType.WITHDRAW;
            } else {
                eventType = EventType.DEPOSIT;
            }
        }

        saveTransaction(new BalanceEvent(System.currentTimeMillis(), uuid,
                eventType, Math.abs(priceDiff)));

    }
}
