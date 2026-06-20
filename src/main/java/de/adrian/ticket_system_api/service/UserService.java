package de.adrian.ticket_system_api.service;

import java.util.List;
import de.adrian.ticket_system_api.entity.User;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
    
    //Todo add register and login method

}
