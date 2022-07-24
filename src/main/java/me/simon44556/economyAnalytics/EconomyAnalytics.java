package me.simon44556.economyAnalytics;

import java.util.Optional;

import org.bukkit.plugin.java.JavaPlugin;

import me.simon44556.economyAnalytics.PlanQueryProvider.PlanQueryProvider;
import me.simon44556.economyAnalytics.PlanQueryProvider.PlanQueryProviderHook;
import me.simon44556.economyAnalytics.PlanWebHandler.PlanWebHandler;
import me.simon44556.economyAnalytics.ShopListener.ShopListener;

public class EconomyAnalytics extends JavaPlugin {
    PlanQueryProvider _planQueryProvider;

    PlanWebHandler _WebHandler;

    @Override
    public void onEnable() {
        enablePlan();

        this.getServer().getPluginManager().registerEvents(new ShopListener(this), this);
        _WebHandler = new PlanWebHandler();
    }

    @Override
    public void onDisable() {

    }

    private void enablePlan() {
        try {
            Optional<PlanQueryProvider> plan = new PlanQueryProviderHook().hookInto();
            if (!plan.isPresent()) {
                throw new Error("Error getting plan provider");
            }
            _planQueryProvider = plan.get();
        } catch (NoClassDefFoundError planIsNotInstalled) {
            // TODO: LOG
        } catch (Error otherError) {
            // TODO: LOG
        }
    }

    public PlanQueryProvider getPlanProvider() {
        return _planQueryProvider;
    }
}
