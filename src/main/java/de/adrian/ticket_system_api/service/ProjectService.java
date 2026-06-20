package de.adrian.ticket_system_api.service;

import java.util.List;
import de.adrian.ticket_system_api.entity.Project;

public interface ProjectService {

    Project createProject(Project project);

    List<Project> getAllProjects();

    Project updateProject(Long id, Project project);
    
    void deleteProject(Long id);
}
