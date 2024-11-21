package dat.dtos;

import com.fasterxml.jackson.annotation.JacksonInject;
import dat.entities.Item;
import dat.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor


public class ItemDTO {
    private Integer id;
    private String name;
    private Double purchasePrice;
    private Category category;
    private Date acquisitionDate;
    private String description;
    //private StudentId studentId;

    public ItemDTO(String name, Double purchasePrice, Category category, Date acquisitionDate, String description) {
    this.name = name;
    this.purchasePrice = purchasePrice;
    this.category = category;
    this.acquisitionDate = acquisitionDate;
    this.description = description;
    }

    public ItemDTO(Item item){
    this.id = item.getId();
    this.name = item.getName();
    this.purchasePrice = item.getPurchasePrice();
    this.category = item.getCategory();
    this.acquisitionDate = item.getAcquisitionDate();
    this.description = item.getDescription();
    // this.studentId = item.getStudentId() != null ? item.getStudent().getId() : null;
    }


}
