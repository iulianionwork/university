package university.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.password.PasswordChangeRequest;
import university.dto.password.PasswordModifyRequest;
import university.exception.BadRequestException;
import university.exception.NotFoundException;
import university.model.UserCredentials;
import university.repository.UserRepository;

@AllArgsConstructor
@Service
public class PasswordService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void userRequestPasswordModification(PasswordChangeRequest request) {
        UserCredentials userCredentials = userService.get();

        if(!passwordEncoder.matches(request.oldPassword(), userCredentials.getPassword())) {
            throw new BadRequestException("Passwords do not match");
        }
        userCredentials.setPassword(passwordEncoder.encode(request.newPassword()));
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void adminRequestPasswordChange(PasswordModifyRequest request) {
        UserCredentials userCredentials = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("User with email: " + request.email() + " not found"));

        userCredentials.setPassword(passwordEncoder.encode(request.newPassword()));
    }
}
