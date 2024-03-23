package com.ashlesh.jobms.mapper;

import com.ashlesh.jobms.dto.JobDto;
import com.ashlesh.jobms.external.Company;
import com.ashlesh.jobms.external.Review;
import com.ashlesh.jobms.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDto mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){

        JobDto jobDto = new JobDto();

        jobDto.setCompany(company);
        jobDto.setDescription(job.getDescription());
        jobDto.setLocation(job.getLocation());
        jobDto.setDescription(job.getDescription());
        jobDto.setTitle(job.getTitle());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setId(job.getId());

        jobDto.setReviews(reviews);


            return jobDto;
    }


}
