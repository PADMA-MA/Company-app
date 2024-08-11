package com.example.Jobapp.job.impl;

import com.example.Jobapp.job.Job;
import com.example.Jobapp.job.JobRepository;
import com.example.Jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    //private Long nextId = 1l;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
//        return jobs;
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
        // jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        return jobs.stream().filter(job -> job.getId().equals(id))
//                .findFirst().orElse(null);
        return jobRepository.findById(id).orElse(null);
    }

//    @Override
//    public boolean deleteById(Long id) {
//        jobs.stream().filter(job -> job.getId().equals(id));
//        return false;
//    }

    @Override
    public boolean deleteById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()) {
//            Job job = iterator.next();
//            if (job.getId().equals(id)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job=jobOptional.get();
       // for (Job job : jobs) {
           // if (job.getId().equals(id)) {
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
