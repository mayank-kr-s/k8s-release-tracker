package com.gitops.tracker.repository;

import com.gitops.tracker.model.DeploymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeploymentRepository extends JpaRepository<DeploymentRecord, Long> {
}