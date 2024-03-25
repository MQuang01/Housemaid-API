package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.JobReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IJobService {
    List<JobResDto> getAllJobs();

    JobResDto findJobById(Long id);

    JobResDto addJob(JobReqDto job, MultipartFile avatar) throws IOException;

    boolean updateJob(Long id, JobReqDto job);

    boolean deleteJob(Long id);

    Page<JobListResDto> findAllWithSearch(String search, Long categoryId, Pageable pageable);
}
