package university.model.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import university.model.entity.UserCredentials;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);


}
