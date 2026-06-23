package de.adrian.ticket_system_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import de.adrian.ticket_system_api.entity.Project;

public interface ProjectService {

    Project createProject(Project project);

    Page<Project> getAllProjects(Pageable pageable);

    Project updateProject(Long id, Project project);
    
    void deleteProject(Long id);
}
