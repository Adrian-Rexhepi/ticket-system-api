package de.adrian.ticket_system_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.adrian.ticket_system_api.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
