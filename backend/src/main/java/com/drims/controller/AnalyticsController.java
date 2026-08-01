package com.drims.controller;

import com.drims.repository.DatasetRepository;
import com.drims.repository.ProjectRepository;
import com.drims.repository.PublicationRepository;
import com.drims.repository.ResearcherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final ResearcherRepository researcherRepository;
    private final PublicationRepository publicationRepository;

    public AnalyticsController(ProjectRepository projectRepository, DatasetRepository datasetRepository,
                               ResearcherRepository researcherRepository, PublicationRepository publicationRepository) {
        this.projectRepository = projectRepository;
        this.datasetRepository = datasetRepository;
        this.researcherRepository = researcherRepository;
        this.publicationRepository = publicationRepository;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        long totalProjects = projectRepository.count();
        Long activeProjects = projectRepository.countActiveProjects();
        BigDecimal totalFunding = projectRepository.getTotalFunding();

        long totalDatasets = datasetRepository.count();
        Double totalStorage = datasetRepository.getTotalStorageMb();

        long totalResearchers = researcherRepository.count();
        long totalPublications = publicationRepository.count();
        Integer totalCitations = publicationRepository.getTotalCitations();

        metrics.put("totalProjects", totalProjects);
        metrics.put("activeProjects", activeProjects != null ? activeProjects : 0);
        metrics.put("totalFunding", totalFunding != null ? totalFunding : BigDecimal.ZERO);
        metrics.put("totalDatasets", totalDatasets);
        metrics.put("totalStorageMb", totalStorage != null ? totalStorage : 0.0);
        metrics.put("totalResearchers", totalResearchers);
        metrics.put("totalPublications", totalPublications);
        metrics.put("totalCitations", totalCitations != null ? totalCitations : 0);

        return ResponseEntity.ok(metrics);
    }
}
