package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.domain.Job;
import com.cghue.projecthousemaidwebapp.domain.dto.req.JobReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeJob;
import com.cghue.projecthousemaidwebapp.repository.ICategoryRepository;
import com.cghue.projecthousemaidwebapp.repository.IJobRepository;
import com.cghue.projecthousemaidwebapp.service.IJobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService implements IJobService {
    private final IJobRepository iJobRepository;
    private final ICategoryRepository iCategoryRepository;
    private final UploadService uploadService;

    @Override
    public JobResDto findJobById(Long id) {
        return iJobRepository.findById(id).map(Job::toResDto).orElse(null);
    }

    @Override
    public JobResDto addJob(JobReqDto job, MultipartFile avatar) throws IOException {
        FileInfo fileInfo = uploadService.saveAvatar(avatar);
        Job newJob = new Job();
        newJob.setName(job.getName());
        newJob.setPrice(job.getPrice());
        newJob.setTimeApprox(job.getTimeApprox());
        newJob.setTypeJob(ETypeJob.valueOf(job.getTypeJob()));
        newJob.setTimeApprox(Integer.valueOf(job.getTimeApprox()));
        newJob.setCategory(iCategoryRepository.findById(job.getCategoryId()).orElse(null));
        newJob.setFileInfo(fileInfo);
        iJobRepository.save(newJob);

        return newJob.toResDto();
    }

    @Override
    public boolean updateJob(Long id, JobReqDto newJob) {
        Job job = iJobRepository.findById(id).orElse(null);
        if (job != null) {
            if (!job.getName().equals(newJob.getName())) {
                job.setName(newJob.getName());
            }
            if (!job.getTimeApprox().equals(newJob.getTimeApprox())) {
                job.setTimeApprox(newJob.getTimeApprox());
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
        Job job = iJobRepository.findById(id).orElse(null);
        if (job != null) {
            iJobRepository.delete(job);
            return true;
        }
        return false;
    }

    @Override
    public Page<JobListResDto> findAllWithSearch(String search, Long categoryId, Pageable pageable) {
        search = "%" + search + "%";
        return iJobRepository.findAllWithSearch(search, categoryId, pageable);
    }

    @Override
    public List<JobListResDto> getJobsByCategoryId(Long id) {
        return iJobRepository.getJobsByCategoryId(id);
    }
}
