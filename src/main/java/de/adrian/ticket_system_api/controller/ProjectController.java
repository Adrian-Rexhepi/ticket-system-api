package de.adrian.ticket_system_api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.adrian.ticket_system_api.dto.ProjectResponse;
import de.adrian.ticket_system_api.entity.Project;
import de.adrian.ticket_system_api.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponse createProject(@RequestBody @Valid ProjectResponse projectRequest) {

        Project project = new Project();

        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());

        Project createdProject = projectService.createProject(project);
        
        return new ProjectResponse(createdProject.getId(), 
        createdProject.getName(), 
        createdProject.getDescription());
    }

    @GetMapping
    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectService.getAllProjects(pageable)
                .map(project -> new ProjectResponse(
                    project.getId(), 
                    project.getName(), 
                    project.getDescription()));
    }
}
