package com.drims.repository;

import com.drims.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
    Optional<Researcher> findByEmail(String email);
    List<Researcher> findByDepartment(String department);
}
