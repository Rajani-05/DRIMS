package com.drims.controller;

import com.drims.model.Researcher;
import com.drims.repository.ResearcherRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/researchers")
@CrossOrigin(origins = "*")
public class ResearcherController {

    private final ResearcherRepository researcherRepository;

    public ResearcherController(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Researcher>> getAllResearchers(@RequestParam(required = false) String department) {
        if (department != null && !department.trim().isEmpty()) {
            return ResponseEntity.ok(researcherRepository.findByDepartment(department.trim()));
        }
        return ResponseEntity.ok(researcherRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Researcher> getResearcherById(@PathVariable Long id) {
        return researcherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Researcher> createResearcher(@Valid @RequestBody Researcher researcher) {
        Researcher saved = researcherRepository.save(researcher);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
