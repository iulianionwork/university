package university.model.service;

import university.controller.dto.UserDTO;

import university.model.entity.User;

public interface UserService {

    User registerUser(UserDTO userDTO);
    void deleteUser(String username);
    void updateUser(UserDTO userDTO);
}
