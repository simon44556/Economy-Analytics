package me.simon44556.economyAnalytics.PlanWebHandler;

import java.util.Optional;

import com.djrapitops.plan.delivery.web.ResolverService;
import com.djrapitops.plan.delivery.web.ResourceService;
import com.djrapitops.plan.delivery.web.ResourceService.Position;
import com.djrapitops.plan.delivery.web.resolver.MimeType;
import com.djrapitops.plan.delivery.web.resolver.Resolver;
import com.djrapitops.plan.delivery.web.resolver.Response;
import com.djrapitops.plan.delivery.web.resolver.request.Request;

public class PlanWebHandler implements Resolver {
    ResolverService _service;
    private final String webPath = "/economy/shop/";

    public PlanWebHandler() {
        _service = ResolverService.getInstance();

        if (!_service.getResolver(webPath).isPresent()) {
            _service.registerResolver("test", webPath, this);
        }

    }

    @Override
    public boolean canAccess(Request request) {
        return true;
    }

    @Override
    public Optional<Response> resolve(Request request) {

        ResourceService svc = ResourceService.getInstance();
        svc.addStylesToResource("TEST", "TEST", Position.PRE_MAIN_SCRIPT, "TEST");

        return Optional.of(
                newResponseBuilder()
                        // calls to builder methods
                        .setMimeType(MimeType.HTML)
                        .setContent("TES22T")
                        .build());
    }

}
