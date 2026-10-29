package university.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "course")
@Getter @Setter @NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @ManyToOne()
    @JoinColumn(name = "professor", nullable = false)
    private Professor professor;

    @ManyToMany(mappedBy = "courses",fetch =  FetchType.LAZY)
    private List<Student> students;

    public Course(String courseName, Professor professor) {
        this.courseName = courseName;
        this.professor = professor;
    }

}
