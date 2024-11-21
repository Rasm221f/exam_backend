package dat.routes;

import dat.controllers.impl.ItemController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ItemRoute {
    private final ItemController itemController = new ItemController();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/category/{category}", itemController::getByCategory);
            post("/populate", itemController::populate);
            post("/", itemController::addNewItem);
            get("/", itemController::getAllItems);
            get("/{id}", itemController::getById);
            put("/{id}", itemController::updateItem);
            delete("/{id}", itemController::deleteItem);
            put("/{itemId}/students/{studentId}", itemController::addItemToStudent);
        };
    }

}
