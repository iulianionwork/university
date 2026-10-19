package university.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.util.Date;

@Entity
@Table(name = "carnet")
@Getter @Setter @NoArgsConstructor
public class Carnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    private Student student;

    public Carnet(Student student) {
        this.student = student;
        this.issueDate = new Date();
    }

}
