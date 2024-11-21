package dat.entities;

import dat.dtos.ItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dat.enums.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dat.entities.Student;

@Getter
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Setter
    @Column(name = "acquisition_date", nullable = false)
    private Date acquisitionDate;

    @Setter
    @Column(name = "description", nullable = false)
    private String description;

    public Student getStudent() {
        return student;
    }

    @Setter
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getName();
        this.purchasePrice = itemDTO.getPurchasePrice();
        this.category = itemDTO.getCategory();
        this.acquisitionDate = itemDTO.getAcquisitionDate();
        this.description = itemDTO.getDescription();
    }
    public Item(String name, Double purchasePrice, Category category, Date acquisitionDate, String description) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.acquisitionDate = acquisitionDate;
        this.description = description;
    }


}
