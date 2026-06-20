package de.adrian.ticket_system_api.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import de.adrian.ticket_system_api.entity.Project;
import de.adrian.ticket_system_api.repository.ProjectRepository;
import de.adrian.ticket_system_api.service.ProjectService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }
    

}
