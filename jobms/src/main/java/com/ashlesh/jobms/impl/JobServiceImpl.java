package com.ashlesh.jobms.impl;

import com.ashlesh.jobms.clients.CompanyClient;
import com.ashlesh.jobms.clients.ReviewClient;
import com.ashlesh.jobms.dto.JobDto;
import com.ashlesh.jobms.external.Company;
import com.ashlesh.jobms.external.Review;
import com.ashlesh.jobms.mapper.JobMapper;
import com.ashlesh.jobms.model.Job;
import com.ashlesh.jobms.repository.JobRepository;
import com.ashlesh.jobms.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    int attempt=0;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;


    @Override
   // @CircuitBreaker(name ="companyBreaker", fallbackMethod = "companyBreakerFallback")
  //  @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
     @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")

    public List<JobDto> findAll() {
        List<Job> jobs = jobRepository.findAll();
        System.out.println("Attempt: "+ ++attempt);

        return jobs.stream().map(this::covertToDto)
                .collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Service Down, Try again Later");
        return list;
    }


    private JobDto covertToDto(Job job){

//        Company company = restTemplate.getForObject(
//                "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
//                Company.class);

        //using FeignClient

        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
//
//
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });

//        List<Review> reviews = reviewResponse.getBody();

        JobDto jobDto = JobMapper.mapToJobWithCompanyDto(job,company, reviews);

        return jobDto;

    }

//    @Override
//    public List<JobWithCompanyDto> findAll() {
//        List<Job> jobs = jobRepository.findAll();
//
//        List<JobWithCompanyDto> jobWithCompanyDTOs = new ArrayList<>();
//
//      // RestTemplate restTemplate = new RestTemplate();
//
//        for (Job job : jobs) {
//            JobWithCompanyDto jobWithCompanyDTO = new JobWithCompanyDto();
//            jobWithCompanyDTO.setJob(job);
//
//            Company company = restTemplate.getForObject(
//                    "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
//                    Company.class);
//
//            jobWithCompanyDTO.setCompany(company);
//
//            jobWithCompanyDTOs.add(jobWithCompanyDTO);
//        }
//
//            return jobWithCompanyDTOs;
//    }

    @Override
    public void createJob(Job job) {
      //  jobs.add(job);
        jobRepository.save(job);

    }

    @Override
    public JobDto getJobById(Long id) {

//        for (Job job: jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;
        Job job = jobRepository.findById(id).orElse(null);

        return covertToDto(job);

    }

    @Override
    public Boolean  deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id))
//            {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;

           try {
               jobRepository.deleteById(id);
               return true;
           }
            catch (Exception e){
               return false;
            }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);

            if(jobOptional.isPresent()){

                Job job = jobOptional.get();

                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());

                jobRepository.save(job);

                return true;

        }
        return false;
    }
}
