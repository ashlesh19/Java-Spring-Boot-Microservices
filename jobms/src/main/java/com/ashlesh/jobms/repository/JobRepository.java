package com.ashlesh.jobms.repository;

import com.ashlesh.jobms.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
