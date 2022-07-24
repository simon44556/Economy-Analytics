package me.simon44556.economyAnalytics.PlanQueryProvider;

import java.util.Optional;

import com.djrapitops.plan.capability.CapabilityService;
import com.djrapitops.plan.query.QueryService;

public class PlanQueryProviderHook {
    public PlanQueryProviderHook() {

    }

    public Optional<PlanQueryProvider> hookInto() {
        if (!areAllCapabilitiesAvailable())
            return Optional.empty();
        return Optional.ofNullable(createAccessor());
    }

    private boolean areAllCapabilitiesAvailable() {
        CapabilityService capabilities = CapabilityService.getInstance();
        return capabilities.hasCapability("QUERY_API");
    }

    private PlanQueryProvider createAccessor() {
        try {
            return new EconomyTracker(QueryService.getInstance());
        } catch (IllegalStateException planIsNotEnabled) {
            // Plan is not enabled, handle exception
            return null;
        }
    }
}
