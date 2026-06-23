package de.adrian.ticket_system_api.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import de.adrian.ticket_system_api.entity.User;
import de.adrian.ticket_system_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new SecurityUser(
        user.getUsername(), 
        user.getPassword(), 
        Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
