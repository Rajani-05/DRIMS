package com.drims.repository;

import com.drims.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByPublicationYear(Integer year);

    @Query("SELECT SUM(p.citationsCount) FROM Publication p")
    Integer getTotalCitations();
}
