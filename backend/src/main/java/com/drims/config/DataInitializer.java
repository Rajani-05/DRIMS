package com.drims.config;

import com.drims.model.Dataset;
import com.drims.model.Publication;
import com.drims.model.ResearchProject;
import com.drims.model.Researcher;
import com.drims.repository.DatasetRepository;
import com.drims.repository.PublicationRepository;
import com.drims.repository.ProjectRepository;
import com.drims.repository.ResearcherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final ResearcherRepository researcherRepository;
    private final PublicationRepository publicationRepository;

    public DataInitializer(ProjectRepository projectRepository, DatasetRepository datasetRepository,
                           ResearcherRepository researcherRepository, PublicationRepository publicationRepository) {
        this.projectRepository = projectRepository;
        this.datasetRepository = datasetRepository;
        this.researcherRepository = researcherRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (projectRepository.count() == 0) {
            // Seed Projects
            ResearchProject p1 = new ResearchProject("Genomic Sequence Variant Mapping", "Large-scale DNA variant indexing and structural variant modeling.", "Bioinformatics", "Dr. Aris Thorne", new BigDecimal("1250000.00"), "ONGOING", LocalDate.of(2024, 1, 15), LocalDate.of(2026, 12, 31));
            ResearchProject p2 = new ResearchProject("Quantum Cryptographic Protocol Testing", "Post-quantum key distribution and grid defense framework.", "Cybersecurity", "Dr. Elena Rostova", new BigDecimal("850000.00"), "ONGOING", LocalDate.of(2023, 6, 1), LocalDate.of(2025, 8, 30));
            ResearchProject p3 = new ResearchProject("Deep Sea Thermal Anomaly Modeling", "Autonomous subsea telemetry and ocean temperature analytics.", "Climate Science", "Prof. Marcus Vance", new BigDecimal("450000.00"), "COMPLETED", LocalDate.of(2022, 3, 10), LocalDate.of(2024, 2, 28));
            ResearchProject p4 = new ResearchProject("Neuromorphic Edge AI Accelerator", "Ultra-low latency spiking neural network design for embedded devices.", "AI/ML", "Dr. Sarah Lin", new BigDecimal("1900000.00"), "ONGOING", LocalDate.of(2024, 4, 1), LocalDate.of(2027, 4, 1));
            projectRepository.saveAll(List.of(p1, p2, p3, p4));

            // Seed Datasets
            Dataset d1 = new Dataset("Human Genome Exome Variant Index (v4.2)", "High-coverage WES variant call format file dataset.", "VCF", 4250.5, "RESTRICTED", p1.getId(), "Dr. Aris Thorne");
            Dataset d2 = new Dataset("Quantum Key Exchange Telemetry Logs", "Raw bit error rate logs from 50km fiber testbed.", "JSON", 620.0, "CONFIDENTIAL", p2.getId(), "Dr. Elena Rostova");
            Dataset d3 = new Dataset("Pacific Basin Thermal Sensors 2023-2024", "Time-series sea surface and abyssal temperatures.", "CSV", 1890.2, "PUBLIC", p3.getId(), "Prof. Marcus Vance");
            Dataset d4 = new Dataset("Spiking Neural Net Spike Train Samples", "Synthetic and recorded neuromorphic sensor stream arrays.", "Parquet", 3100.8, "PUBLIC", p4.getId(), "Dr. Sarah Lin");
            datasetRepository.saveAll(List.of(d1, d2, d3, d4));

            // Seed Researchers
            Researcher r1 = new Researcher("Dr. Aris Thorne", "aris.thorne@drims.org", "Genomics & Bio-Data", "Principal Investigator", "0000-0002-1825-0097", 14);
            Researcher r2 = new Researcher("Dr. Elena Rostova", "elena.rostova@drims.org", "Cybersecurity", "Lead Cryptographer", "0000-0001-9034-4412", 22);
            Researcher r3 = new Researcher("Prof. Marcus Vance", "marcus.vance@drims.org", "Oceanography", "Senior Scientist", "0000-0003-7721-1189", 35);
            Researcher r4 = new Researcher("Dr. Sarah Lin", "sarah.lin@drims.org", "Computer Science", "AI Research Chair", "0000-0002-4510-8890", 19);
            researcherRepository.saveAll(List.of(r1, r2, r3, r4));

            // Seed Publications
            Publication pub1 = new Publication("High-Throughput Variant Search via Indexed B-Trees", "Journal of Computational Biology", 2024, "10.1016/j.jcb.2024.01.004", 48, "Dr. Aris Thorne");
            Publication pub2 = new Publication("Lattice-Based Encryption Performance under Thermal Stress", "IEEE Transactions on Information Theory", 2023, "10.1109/TIT.2023.991201", 112, "Dr. Elena Rostova");
            Publication pub3 = new Publication("Decadal Trends in Abyssal Pacific Temperatures", "Nature Climate Change", 2024, "10.1038/s41558-024-01955-x", 87, "Prof. Marcus Vance");
            publicationRepository.saveAll(List.of(pub1, pub2, pub3));

            System.out.println(">>> DRIMS Initial Seed Data Successfully Populated into Database.");
        }
    }
}
