package de.adrian.ticket_system_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.adrian.ticket_system_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
