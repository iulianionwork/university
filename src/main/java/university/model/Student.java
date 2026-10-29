package university.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "student")
@Getter @Setter @NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "student",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private Carnet carnet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_group")
    private Group group;

    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = {
            @JoinColumn(name = "student_id", nullable = false)},
            inverseJoinColumns = { @JoinColumn(name = "course_id",nullable = false)}
    )
    private List<Course> courses;

    public Student(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

}
