package com.drims.controller;

import com.drims.model.Dataset;
import com.drims.repository.DatasetRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/datasets")
@CrossOrigin(origins = "*")
public class DatasetController {

    private final DatasetRepository datasetRepository;

    public DatasetController(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    @GetMapping
    public ResponseEntity<List<Dataset>> getAllDatasets(@RequestParam(required = false) String search,
                                                        @RequestParam(required = false) String accessLevel,
                                                        @RequestParam(required = false) Long projectId) {
        if (search != null && !search.trim().isEmpty()) {
            return ResponseEntity.ok(datasetRepository.searchDatasets(search.trim()));
        }
        if (accessLevel != null && !accessLevel.trim().isEmpty()) {
            return ResponseEntity.ok(datasetRepository.findByAccessLevel(accessLevel.trim().toUpperCase()));
        }
        if (projectId != null) {
            return ResponseEntity.ok(datasetRepository.findByProjectId(projectId));
        }
        return ResponseEntity.ok(datasetRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dataset> getDatasetById(@PathVariable Long id) {
        return datasetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dataset> createDataset(@Valid @RequestBody Dataset dataset) {
        dataset.setUpdatedAt(LocalDateTime.now());
        Dataset saved = datasetRepository.save(dataset);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{id}/download")
    public ResponseEntity<Dataset> incrementDownload(@PathVariable Long id) {
        return datasetRepository.findById(id)
                .map(d -> {
                    d.setDownloadsCount(d.getDownloadsCount() + 1);
                    return ResponseEntity.ok(datasetRepository.save(d));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable Long id) {
        if (datasetRepository.existsById(id)) {
            datasetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
