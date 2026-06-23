package de.adrian.ticket_system_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import de.adrian.ticket_system_api.entity.User;

public interface UserService {

    User createUser(User user);

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
    
    //Todo add register and login method

}
