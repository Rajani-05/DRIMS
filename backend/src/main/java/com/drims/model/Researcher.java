package com.drims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "researchers", indexes = {
    @Index(name = "idx_researcher_email", columnList = "email", unique = true),
    @Index(name = "idx_researcher_dept", columnList = "department")
})
public class Researcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String role; // Principal Investigator, Research Fellow, Data Scientist, Postdoc

    @Column(name = "orcid_id", length = 50)
    private String orcidId;

    @Column(name = "publications_count")
    private Integer publicationsCount = 0;

    public Researcher() {}

    public Researcher(String name, String email, String department, String role, String orcidId, Integer publicationsCount) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.role = role;
        this.orcidId = orcidId;
        this.publicationsCount = publicationsCount != null ? publicationsCount : 0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getOrcidId() { return orcidId; }
    public void setOrcidId(String orcidId) { this.orcidId = orcidId; }

    public Integer getPublicationsCount() { return publicationsCount; }
    public void setPublicationsCount(Integer publicationsCount) { this.publicationsCount = publicationsCount; }
}
