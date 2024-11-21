package dat.controllers.impl;

import dat.Populator;
import dat.config.HibernateConfig;
import dat.daos.impl.ItemDAO;
import dat.daos.impl.StudentDAO;
import dat.dtos.ItemDTO;
import dat.dtos.StudentDTO;
import dat.entities.Student;
import dat.enums.Category;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemController {

    private static ItemDAO itemDAO;
    private static StudentDAO studentDAO;

    public ItemController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.itemDAO = ItemDAO.getInstance(emf);
        this.studentDAO = StudentDAO.getInstance(emf);
    }

    public void getById(Context ctx) throws ApiException {
        // request
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));

            ItemDTO itemDTO = itemDAO.read(id);

            ctx.res().setStatus(200);
            ctx.json(itemDTO, ItemDTO.class);

        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        }
    }

    public void getAllItems(Context ctx) throws ApiException {
        Set<ItemDTO> itemDTOs = itemDAO.readAll();
        ctx.res().setStatus(200);
        ctx.json(itemDTOs, ItemDTO.class);
    }

    public void addNewItem(Context ctx) throws ApiException {
        try {
            // request
            ItemDTO jsonRequest = ctx.bodyAsClass(ItemDTO.class);
            // DTO
            ItemDTO itemDTO = itemDAO.create(jsonRequest);
            // response
            ctx.res().setStatus(201);
            ctx.json(itemDTO, ItemDTO.class);
        } catch (Exception e) {
            throw new ApiException(400, e.getMessage());
        }
    }

    public void updateItem(Context ctx) throws ApiException {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ItemDTO jsonRequest = ctx.bodyAsClass(ItemDTO.class);
            // dto
            ItemDTO itemDTO = itemDAO.update(id, jsonRequest);
            // response
            ctx.res().setStatus(200);
            ctx.json(itemDTO, ItemDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        }
    }

    public void deleteItem(Context ctx) throws ApiException {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            itemDAO.delete(id);
            ctx.res().setStatus(204);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        }
    }

    public void addItemToStudent(Context ctx) throws ApiException {
        try {
            Integer itemId = Integer.valueOf(ctx.pathParam("tripId"));

            Integer studentId = Integer.valueOf(ctx.pathParam("guideId"));

            itemDAO.addItemToStudent(itemId, studentId);


            if (itemId != 0 && studentId != 0) {
                ctx.status(200);
            } else {
                throw new ApiException(204, "No content. Item not added");
            }

        } catch (ApiException e) {
            throw new ApiException(400, e.getMessage());
        }
    }


    public void populate(Context ctx) throws ApiException {
        Populator populator = new Populator();

    }

    public void getByCategory(Context ctx) throws ApiException {
        try {
            Category category = Category.valueOf(ctx.pathParam("category").toUpperCase());
            List<ItemDTO> itemDTOS = itemDAO.getByCategory(category);
            ctx.res().setStatus(200);
            ctx.json(itemDTOS, ItemDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: category");
        }
    }
//
//    public void getTotalPurchasePriceByStudent(Context ctx) throws ApiException {
//        try {
//            Set<StudentDTO> studentDTOS = studentDAO.readAll();
//            Set<ItemDTO> itemDTOS = itemDAO.readAll();
//
//            Map<String, Double> studentTotalPriceMap = studentDTOS.stream()
//                    .collect(Collectors.toMap(
//                            StudentDTO::getName, // Key: Student name
//                            student -> itemDTOS.stream()
//                                    .filter(item -> item.getStudent() != null && item.getStudent().getId().equals(student.getId()))
//                                    .mapToDouble(ItemDTO::getPurchasePrice)
//                                    .sum() // Calculate the total purchase price for the student
//                    ));
//            ctx.json(studentTotalPriceMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ApiException(500, "Failed to summarize total purchase prices: " + e.getMessage());
//        }
//}

}
