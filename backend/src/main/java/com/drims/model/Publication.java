package com.drims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "publications", indexes = {
    @Index(name = "idx_pub_doi", columnList = "doi"),
    @Index(name = "idx_pub_year", columnList = "publication_year")
})
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Publication title is required")
    @Column(nullable = false, length = 300)
    private String title;

    @Column(length = 255)
    private String journal;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(length = 100)
    private String doi;

    @Column(name = "citations_count")
    private Integer citationsCount = 0;

    @Column(name = "lead_author", length = 150)
    private String leadAuthor;

    public Publication() {}

    public Publication(String title, String journal, Integer publicationYear, String doi, Integer citationsCount, String leadAuthor) {
        this.title = title;
        this.journal = journal;
        this.publicationYear = publicationYear;
        this.doi = doi;
        this.citationsCount = citationsCount != null ? citationsCount : 0;
        this.leadAuthor = leadAuthor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getJournal() { return journal; }
    public void setJournal(String journal) { this.journal = journal; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }

    public Integer getCitationsCount() { return citationsCount; }
    public void setCitationsCount(Integer citationsCount) { this.citationsCount = citationsCount; }

    public String getLeadAuthor() { return leadAuthor; }
    public void setLeadAuthor(String leadAuthor) { this.leadAuthor = leadAuthor; }
}
