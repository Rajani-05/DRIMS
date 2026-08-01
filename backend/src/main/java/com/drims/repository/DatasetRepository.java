package com.drims.repository;

import com.drims.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {

    List<Dataset> findByAccessLevel(String accessLevel);

    List<Dataset> findByFileFormat(String fileFormat);

    List<Dataset> findByProjectId(Long projectId);

    @Query("SELECT d FROM Dataset d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(d.fileFormat) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Dataset> searchDatasets(@Param("query") String query);

    @Query("SELECT SUM(d.sizeMb) FROM Dataset d")
    Double getTotalStorageMb();
}
