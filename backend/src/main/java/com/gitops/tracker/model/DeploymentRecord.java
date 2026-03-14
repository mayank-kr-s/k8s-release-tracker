package com.gitops.tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Application name is required")
    private String applicationName;

    @NotBlank(message = "Environment (e.g., dev, prod) is required")
    private String environment;

    private String version;
    
    private String status; // SUCCESS or FAILED

    private LocalDateTime deploymentTime = LocalDateTime.now();
}