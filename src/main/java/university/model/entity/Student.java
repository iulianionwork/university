package university.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "student")
@Getter @Setter @NoArgsConstructor @ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "university_group")
    private Group group;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_course", joinColumns = {
            @JoinColumn(name = "student_id", nullable = false)},
            inverseJoinColumns = { @JoinColumn(name = "course_id",nullable = false)}
    )
    private List<Course> courses;

    public Student(String firstName, String lastName, String email) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

}
