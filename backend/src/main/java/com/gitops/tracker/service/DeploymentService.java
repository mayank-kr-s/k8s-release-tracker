package com.gitops.tracker.service;

import com.gitops.tracker.model.DeploymentRecord;
import com.gitops.tracker.repository.DeploymentRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeploymentService {

    private final DeploymentRepository repository;
    private final Counter successCounter;
    private final Counter failureCounter;

    // Injecting MeterRegistry to create custom metrics for Grafana
    public DeploymentService(DeploymentRepository repository, MeterRegistry meterRegistry) {
        this.repository = repository;
        this.successCounter = meterRegistry.counter("deployments.status", "result", "success");
        this.failureCounter = meterRegistry.counter("deployments.status", "result", "failure");
    }

    public List<DeploymentRecord> getAllDeployments() {
        return repository.findAll();
    }

    public DeploymentRecord recordDeployment(DeploymentRecord record) {
        if ("SUCCESS".equalsIgnoreCase(record.getStatus())) {
            successCounter.increment();
        } else {
            failureCounter.increment();
        }
        return repository.save(record);
    }
}