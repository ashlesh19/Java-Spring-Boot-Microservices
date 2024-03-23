package com.ashlesh.jobms.service;


import com.ashlesh.jobms.dto.JobDto;
import com.ashlesh.jobms.model.Job;

import java.util.List;

public interface JobService {

    List<JobDto> findAll();

    void createJob(Job job);

    JobDto getJobById(Long id);

    Boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
