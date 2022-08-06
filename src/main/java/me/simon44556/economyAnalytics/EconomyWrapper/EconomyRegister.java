package me.simon44556.economyAnalytics.EconomyWrapper;

import java.util.Optional;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import me.simon44556.economyAnalytics.EconomyAnalytics;
import me.simon44556.economyAnalytics.EconomyEvents.EconomyEventsAvailable;
import net.milkbowl.vault.economy.Economy;

public class EconomyRegister {
    private boolean eventsAvailable = false;
    private final EconomyAnalytics plugin;
    Logger logger;

    public EconomyRegister(EconomyAnalytics plugin) {
        this.plugin = plugin;
        this.logger = Bukkit.getLogger();
        PluginManager plugins = Bukkit.getServer().getPluginManager();
        if (!plugins.isPluginEnabled("Vault")) {
            logger.warning("Vault is required for Vault Events -> Economy events not working.");
            return;
        }

        Optional<Economy> economy = getEconomy();
        if (economy.isPresent()) {
            registerWrapper(economy.get());
        } else {
            tryAgainOnStart();
        }
    }

    public boolean areEventsAvailable() {
        return eventsAvailable;
    }

    public boolean supportsEconomyEvents() {
        return true;
    }

    public boolean supportsPermissionEvents() {
        return false; // Vault Permission based events are not supported at the moment. PR if you'd
                      // like to add them.
    }

    public boolean supportsChatEvents() {
        return false; // Vault Chat based events are not supported at the moment. PR if you'd like to
                      // add them.
    }

    private void tryAgainOnStart() {
        Bukkit.getServer().getScheduler().runTaskLater(plugin,
                () -> getEconomy().ifPresent(this::registerWrapper),
                0); // Run when server has done loading
    }

    private Optional<Economy> getEconomy() {
        ServicesManager services = Bukkit.getServer().getServicesManager();
        RegisteredServiceProvider<Economy> economyService = services.getRegistration(Economy.class);
        if (economyService == null)
            return Optional.empty();
        Economy economy = economyService.getProvider();
        return Optional.ofNullable(economy);
    }

    private void registerWrapper(Economy original) {
        // Don't wrap Economy twice in case of reloads.
        Economy wrappedEco = original instanceof EconomyWrapper ? original : new EconomyWrapper(original);
        Bukkit.getServer().getServicesManager().register(Economy.class, wrappedEco, plugin, ServicePriority.Highest);
        logger.info("Vault Events registered - Events can now be listened to.");
        eventsAvailable = true;
        Bukkit.getPluginManager().callEvent(new EconomyEventsAvailable(wrappedEco));
    }
}
