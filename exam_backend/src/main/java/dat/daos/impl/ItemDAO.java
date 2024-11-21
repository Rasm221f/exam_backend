package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.ItemDTO;
import dat.entities.Item;
import dat.entities.Student;
import dat.enums.Category;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemDAO implements IDAO<ItemDTO, Integer> {

    private static ItemDAO instance;
    private static EntityManagerFactory emf;


    public static ItemDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemDAO();
        }
        return instance;
    }

    @Override
    public ItemDTO create(ItemDTO itemDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Item item = new Item(itemDTO);
            em.persist(item);
            em.getTransaction().commit();
            return new ItemDTO(item);
        }
    }

    @Override
    public ItemDTO read(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
        Item item = em.find(Item.class, id);
        return new ItemDTO(item);
        }
    }

    @Override
    public ItemDTO update(Integer id, ItemDTO itemDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Item i = em.find(Item.class, id);
            i.setCategory(itemDTO.getCategory());
            i.setName(itemDTO.getName());
            i.setPurchasePrice(itemDTO.getPurchasePrice());
            i.setDescription(itemDTO.getDescription());
            Item mergedItem = em.merge(i);
            em.getTransaction().commit();
            return mergedItem != null ? new ItemDTO(mergedItem) : null;
        }
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Item item = em.find(Item.class, id);
            if (item != null) {
                em.remove(item);
            }
            em.getTransaction().commit();
        }
    }
    public Set<ItemDTO> readAll() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ItemDTO> query = em.createQuery("SELECT new dat.dtos.ItemDTO(t) FROM Item t", ItemDTO.class);
            return query.getResultStream().collect(Collectors.toSet());
        } catch (PersistenceException e) {
            throw new ApiException(400, "Something went wrong reading items");
        }
    }
    public List<ItemDTO> getByCategory(Category category) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ItemDTO> query = em.createQuery("SELECT new dat.dtos.ItemDTO(i) FROM Item i WHERE i.category = :category", ItemDTO.class);
            return query.setParameter("category", category).getResultList();
        }
    }

    public void addItemToStudent(Integer itemId, Integer studentId) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Item item = em.find(Item.class, itemId);
            Student student = em.find(Student.class, studentId);
            if (item == null || student == null) {
                throw new ApiException(404, "Item or student not found");
            }
            student.addItem(item);
            em.merge(item);
            em.merge(student);
            em.getTransaction().commit();
        }
    }

    public Set<ItemDTO> getItemsByStudent(Integer studentId) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<ItemDTO> query = em.createQuery("SELECT new dat.dtos.ItemDTO(i) FROM Item i JOIN FETCH i.student s WHERE s.id = :studentId ", ItemDTO.class);

            query.setParameter("studentId", studentId);

            return query.getResultStream().collect(Collectors.toSet());
        } catch (Exception e) {
        throw new ApiException(500, e.getMessage());
        }
    }
}
