package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final ItemRoute itemRoute = new ItemRoute();


    public EndpointGroup getRoutes() {
        return () -> {
            path("/items", itemRoute.getRoutes());
        };
    }
}
