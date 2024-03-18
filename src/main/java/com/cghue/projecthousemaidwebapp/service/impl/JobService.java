package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Job;
import com.cghue.projecthousemaidwebapp.domain.dto.req.JobReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import com.cghue.projecthousemaidwebapp.repository.ICategoryRepository;
import com.cghue.projecthousemaidwebapp.repository.IJobRepository;
import com.cghue.projecthousemaidwebapp.service.IJobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService implements IJobService {
    private final IJobRepository iJobRepository;
    private final ICategoryRepository iCategoryRepository;

    @Override
    public List<JobResDto> getAllJobs() {
        return iJobRepository.findAll().stream().map(Job::toResDto).toList();
    }

    @Override
    public JobResDto findJobById(Long id) {
        return iJobRepository.findById(id).map(Job::toResDto).orElse(null);
    }

    @Override
    public JobResDto addJob(JobReqDto job) {
        Job newJob = new Job();
        newJob.setName(job.getName());
        newJob.setUrlImage(job.getUrlImage());
        newJob.setPrice(job.getPrice());
        newJob.setCategory(iCategoryRepository.findById(job.getCategoryId()).orElse(null));
        iJobRepository.save(newJob);
        return newJob.toResDto();
    }

    @Override
    public boolean updateJob(Long id, JobReqDto newJob) {
        Job job = iJobRepository.findById(id).orElse(null);
        if (job != null) {
            if(!job.getName().equals(newJob.getName())) {
                job.setName(newJob.getName());
            }
            if (!job.getUrlImage().equals(newJob.getUrlImage())) {
                job.setUrlImage(newJob.getUrlImage());
            }
            if (!job.getPrice().equals(newJob.getPrice())) {
                job.setPrice(newJob.getPrice());
            }
            if (!job.getCategory().getId().equals(newJob.getCategoryId())) {
                job.setCategory(iCategoryRepository.findById(newJob.getCategoryId()).orElse(null));
            }
            iJobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        if (iJobRepository.findById(id).isPresent()) {
            iJobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
