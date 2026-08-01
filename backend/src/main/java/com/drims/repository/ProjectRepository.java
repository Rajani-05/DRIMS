package com.drims.repository;

import com.drims.model.ResearchProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ResearchProject, Long> {

    List<ResearchProject> findByDomainContainingIgnoreCase(String domain);

    List<ResearchProject> findByStatus(String status);

    @Query("SELECT p FROM ResearchProject p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.domain) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.principalInvestigator) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ResearchProject> searchProjects(@Param("query") String query);

    @Query("SELECT SUM(p.grantAmount) FROM ResearchProject p")
    BigDecimal getTotalFunding();

    @Query("SELECT COUNT(p) FROM ResearchProject p WHERE p.status = 'ONGOING'")
    Long countActiveProjects();
}
