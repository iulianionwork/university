package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.UserDTO;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.User;
import university.model.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;



    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public User registerUser(UserDTO userDTO) {
            User user = new User(userDTO.getUsername(), new BCryptPasswordEncoder().encode(userDTO.getPassword()), userDTO.getRole());
            userRepository.save(user);
            Long id = user.getId();

            if(userDTO.getRole().equals(Role.STUDENT)) {
                Student student = new Student(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
                student.setId(id);
                user.setStudent(student);
            }else if(userDTO.getRole().equals(Role.PROFESSOR)) {
                Professor professor = new Professor(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
                professor.setId(id);
                user.setProfessor(professor);
            }
            userRepository.save(user);
            return user;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(UserDTO  userDTO) {
        User user =  userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(userDTO.getRole());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

        userRepository.save(user);
    }
}
