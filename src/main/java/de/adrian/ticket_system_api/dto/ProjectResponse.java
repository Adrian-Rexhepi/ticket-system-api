package de.adrian.ticket_system_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectResponse {

    private Long id;

    private String name;

    private String description;
}
