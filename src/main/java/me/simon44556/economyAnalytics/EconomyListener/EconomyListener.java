package me.simon44556.economyAnalytics.EconomyListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.DataTypes.BalanceEvent;
import me.simon44556.economyAnalytics.EconomyEvents.Economy.PlayerDepositEvent;
import me.simon44556.economyAnalytics.EconomyEvents.Economy.PlayerWithdrawEvent;

public class EconomyListener extends EventListenHandler {
    public EconomyListener(EconomyAnalytics plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEconomyDeposit(PlayerDepositEvent e) {
        saveTransaction(new BalanceEvent(System.currentTimeMillis(), e.getOfflinePlayer().getUniqueId().toString(),
                matchEventType("Withdraw"), Math.abs(e.getAmount())));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEconomyWithdraw(PlayerWithdrawEvent e) {
        saveTransaction(new BalanceEvent(System.currentTimeMillis(), e.getOfflinePlayer().getUniqueId().toString(),
                matchEventType("Deposit"), Math.abs(e.getAmount())));
    }
}
