package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);
    List<UserCredentials> findByRole(Role role);

    @Query("""
            SELECT u FROM UserCredentials u
            JOIN FETCH u.student s
            LEFT JOIN FETCH  s.group
            """)
    List<UserCredentials> findAllStudents();

    @Query("SELECT u FROM UserCredentials u JOIN FETCH u.professor")
    List<UserCredentials> findAllProfessors();

    @Query("""
            SELECT u FROM UserCredentials u
            JOIN FETCH u.student s
            JOIN FETCH  s.group g
                        WHERE g.id = :groupId
            """)
    List<UserCredentials> getStudentsByGroupId(@Param("groupId") Long groupId);

    @Query("""
            SELECT u FROM UserCredentials u
            JOIN FETCH u.student s
            LEFT JOIN FETCH  s.group
            JOIN s.courses c
            WHERE c.id = :courseId
            """)
    List<UserCredentials> getStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("""
            SELECT u FROM UserCredentials u
            JOIN FETCH u.student s
            WHERE  s.group IS NULL
            """)
    List<UserCredentials> getStudentsWithoutGroup();




}
