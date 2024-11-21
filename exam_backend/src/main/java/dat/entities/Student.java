package dat.entities;

import dat.dtos.StudentDTO;
import dat.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Setter
    @Column(name = "enrollment_date", nullable = false)
    private Date enrollmentDate;

    @Setter
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        if ( item != null) {
            this.items.add(item);
            item.setStudent(this);
        }
    }
    public void setItems(Set<Item> items) {
        if(items != null) {
            this.items = items;
            for (Item item : items) {
                item.setStudent(this);
            }
        }
    }
    public Student(StudentDTO studentDTO){
        this.id = studentDTO.getId();
        this.name = studentDTO.getName();
        this.email = studentDTO.getEmail();
        this.enrollmentDate = studentDTO.getEnrollmentDate();
        this.phoneNumber = studentDTO.getPhoneNumber();
        if (studentDTO.getItems() != null) {
            studentDTO.getItems().forEach(itemDTO -> items.add(new Item(itemDTO)));
        }
    }
    public Student(String name, String email, Date enrollmentDate, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.phoneNumber = phoneNumber;
    }

}
