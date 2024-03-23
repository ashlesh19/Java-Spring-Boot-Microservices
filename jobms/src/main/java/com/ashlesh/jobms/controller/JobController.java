package com.ashlesh.jobms.controller;


import com.ashlesh.jobms.dto.JobDto;
import com.ashlesh.jobms.model.Job;
import com.ashlesh.jobms.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;



    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }



    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added",HttpStatus.CREATED);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id){
        JobDto jobDto =  jobService.getJobById(id);
        if(jobDto != null) {
            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean jobdelted = jobService.deleteJobById(id);
        if(jobdelted) {
            return new ResponseEntity<>("Deletion Success", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Deletion failed:", HttpStatus.NOT_FOUND);
    }

    //@PutMapping("/jobs/{id}")  //or
    @RequestMapping(value = "/jobs/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id,updatedJob);
        if(updated) {
            return new ResponseEntity<>("Update Success", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update failed:", HttpStatus.NOT_FOUND);
    }

}
