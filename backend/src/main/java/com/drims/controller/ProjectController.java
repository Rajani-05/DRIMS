package com.drims.controller;

import com.drims.model.ResearchProject;
import com.drims.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public ResponseEntity<List<ResearchProject>> getAllProjects(@RequestParam(required = false) String search,
                                                                 @RequestParam(required = false) String status) {
        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(projectRepository.searchProjects(search.trim()));
        }
        if (status != null && !status.trim().isEmpty()) {
            return ResponseEntity.ok(projectRepository.findByStatus(status.trim().toUpperCase()));
        }
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchProject> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResearchProject> createProject(@Valid @RequestBody ResearchProject project) {
        ResearchProject saved = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchProject> updateProject(@PathVariable Long id, @Valid @RequestBody ResearchProject updated) {
        return projectRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setDomain(updated.getDomain());
                    existing.setPrincipalInvestigator(updated.getPrincipalInvestigator());
                    existing.setGrantAmount(updated.getGrantAmount());
                    existing.setStatus(updated.getStatus());
                    existing.setStartDate(updated.getStartDate());
                    existing.setEndDate(updated.getEndDate());
                    return ResponseEntity.ok(projectRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
