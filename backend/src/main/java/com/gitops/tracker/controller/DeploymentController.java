package com.gitops.tracker.controller;

import com.gitops.tracker.model.DeploymentRecord;
import com.gitops.tracker.service.DeploymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deployments")
@RequiredArgsConstructor
@Tag(name = "Deployment Tracker", description = "API for tracking CI/CD deployments")
public class DeploymentController {

    private final DeploymentService service;

    @GetMapping
    @Operation(summary = "Get all deployment records")
    public List<DeploymentRecord> getAllDeployments() {
        return service.getAllDeployments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Record a new deployment")
    public DeploymentRecord createDeployment(@Valid @RequestBody DeploymentRecord record) {
        return service.recordDeployment(record);
    }
}