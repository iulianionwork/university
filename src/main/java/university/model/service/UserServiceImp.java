package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.UserDTO;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserCredentials> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userCredentials = user.get();
            return User.builder()
                    .username(userCredentials.getUsername())
                    .password(userCredentials.getPassword())
                    .roles(userCredentials.getRole().toString())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
