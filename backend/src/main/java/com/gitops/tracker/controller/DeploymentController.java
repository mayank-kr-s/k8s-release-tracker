package com.gitops.tracker.controller;

import com.gitops.tracker.model.DeploymentRecord;
import com.gitops.tracker.service.DeploymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deployments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Deployment Tracker", description = "API for tracking CI/CD deployments")
public class DeploymentController {

    private final DeploymentService service;

    @GetMapping
    public List<DeploymentRecord> getAllDeployments() {
        return service.getAllDeployments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeploymentRecord createDeployment(@Valid @RequestBody DeploymentRecord record) {
        // 3. ADD THIS LOG STATEMENT
        log.info("Received new deployment simulation for App: {} in Env: {}", 
                 record.getApplicationName(), record.getEnvironment());
        
        return service.recordDeployment(record);
    }
}