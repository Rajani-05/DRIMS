package com.drims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "datasets", indexes = {
    @Index(name = "idx_dataset_access", columnList = "access_level"),
    @Index(name = "idx_dataset_format", columnList = "file_format"),
    @Index(name = "idx_dataset_project", columnList = "project_id")
})
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Dataset name is required")
    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "file_format", length = 50)
    private String fileFormat; // CSV, JSON, NetCDF, FASTA, Parquet, DICOM

    @Column(name = "size_mb")
    private Double sizeMb;

    @Column(name = "access_level", length = 50)
    private String accessLevel; // PUBLIC, RESTRICTED, CONFIDENTIAL

    @Column(name = "downloads_count")
    private Integer downloadsCount = 0;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "uploaded_by", length = 150)
    private String uploadedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Dataset() {
        this.updatedAt = LocalDateTime.now();
    }

    public Dataset(String name, String description, String fileFormat, Double sizeMb, String accessLevel, Long projectId, String uploadedBy) {
        this.name = name;
        this.description = description;
        this.fileFormat = fileFormat;
        this.sizeMb = sizeMb;
        this.accessLevel = accessLevel;
        this.projectId = projectId;
        this.uploadedBy = uploadedBy;
        this.downloadsCount = 0;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFileFormat() { return fileFormat; }
    public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }

    public Double getSizeMb() { return sizeMb; }
    public void setSizeMb(Double sizeMb) { this.sizeMb = sizeMb; }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }

    public Integer getDownloadsCount() { return downloadsCount; }
    public void setDownloadsCount(Integer downloadsCount) { this.downloadsCount = downloadsCount; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
