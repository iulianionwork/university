package university.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Getter @Setter @NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "department", nullable = false)
    private String department;


    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

    public Professor(String firstName, String lastName, String email, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }


}
