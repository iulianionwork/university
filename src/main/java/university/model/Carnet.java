package university.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "carnet")
@Getter @Setter @NoArgsConstructor
public class Carnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "student_number", unique = true, nullable = false)
    private String studentCode;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student;

    public Carnet(Student student, String studentCode) {
        this.student = student;
        this.issueDate = LocalDate.now();
        this.studentCode = studentCode;
    }



}
