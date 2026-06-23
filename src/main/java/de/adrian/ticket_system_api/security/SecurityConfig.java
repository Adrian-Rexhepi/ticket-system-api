package de.adrian.ticket_system_api.security;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfig extends WebSecurityConfigurationAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated();
    }

}
