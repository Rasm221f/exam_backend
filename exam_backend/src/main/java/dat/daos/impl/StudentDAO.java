package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.ItemDTO;
import dat.dtos.StudentDTO;
import dat.entities.Item;
import dat.entities.Student;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentDAO implements IDAO <StudentDTO, Integer> {
    private static StudentDAO instance;
    private static EntityManagerFactory emf;

    public static StudentDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDAO();
        }
        return instance;
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student student = new Student(studentDTO);
            em.persist(student);
            em.getTransaction().commit();
            return new StudentDTO(student);
        }
        catch (Exception e){
            throw new ApiException(400, "Could not create student");
        }
    }

    @Override
    public StudentDTO read(Integer integer) {
        return null;
    }

    public Set<StudentDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<StudentDTO> query = em.createQuery("SELECT new dat.dtos.StudentDTO(s) FROM Student s", StudentDTO.class);
            return query.getResultStream().collect(Collectors.toSet());
        }
    }

    @Override
    public StudentDTO update(Integer integer, StudentDTO studentDTO) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

}
