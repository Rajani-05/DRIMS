package com.drims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "research_projects", indexes = {
    @Index(name = "idx_project_domain", columnList = "domain"),
    @Index(name = "idx_project_status", columnList = "status"),
    @Index(name = "idx_project_pi", columnList = "principal_investigator")
})
public class ResearchProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project title is required")
    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Research domain is required")
    @Column(nullable = false, length = 100)
    private String domain; // e.g., AI/ML, Bioinformatics, Climate Science, Cybersecurity

    @Column(name = "principal_investigator", nullable = false, length = 150)
    private String principalInvestigator;

    @NotNull(message = "Grant amount is required")
    @Column(name = "grant_amount", precision = 15, scale = 2)
    private BigDecimal grantAmount;

    @Column(length = 50)
    private String status; // ONGOING, COMPLETED, PROPOSED, ON_HOLD

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public ResearchProject() {
        this.createdAt = LocalDate.now();
    }

    public ResearchProject(String title, String description, String domain, String principalInvestigator, BigDecimal grantAmount, String status, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.domain = domain;
        this.principalInvestigator = principalInvestigator;
        this.grantAmount = grantAmount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getPrincipalInvestigator() { return principalInvestigator; }
    public void setPrincipalInvestigator(String principalInvestigator) { this.principalInvestigator = principalInvestigator; }

    public BigDecimal getGrantAmount() { return grantAmount; }
    public void setGrantAmount(BigDecimal grantAmount) { this.grantAmount = grantAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}
