package dat.dtos;

import java.util.Date;
import java.util.List;

import dat.entities.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class StudentDTO {

    private Integer id;
    private String name;
    private String email;
    private Date enrollmentDate;
    private String phoneNumber;
    private List<ItemDTO> items;

public StudentDTO(String name, String email, Date enrollmentDate, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.enrollmentDate = enrollmentDate;
    this.phoneNumber = phoneNumber;
}

public StudentDTO(Student student){
    this.id = student.getId();
    this.name = student.getName();
    this.email = student.getEmail();
    this.enrollmentDate = student.getEnrollmentDate();
    this.phoneNumber = student.getPhoneNumber();
}

}
