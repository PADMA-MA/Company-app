package com.example.Jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }
//    @GetMapping("/jobs")
//    public List<Job> findAll() {
//        return jobService.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> fetchJob(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job != null ? new ResponseEntity<>(job, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /*    @GetMapping("/jobs/{id}")
    public Job fetchJob(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job != null ? job : new Job(1l, "sd", "aef",
                "er", "werwrg", "wet4t6h");
    }*/

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added", HttpStatus.CREATED);
    }
//
//    @PostMapping("/jobs")
//    public String createJob(@RequestBody Job job) {
//        jobService.createJob(job);
//        return "Job added";
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return jobService.deleteById(id) ? ResponseEntity.ok("job deleted") :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value="/jobs/{id}" , method=RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("job updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
