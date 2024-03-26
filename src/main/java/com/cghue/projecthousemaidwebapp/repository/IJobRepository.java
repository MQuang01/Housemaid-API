package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.Job;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IJobRepository extends JpaRepository<Job, Long> {

    @Query ("SELECT " +
            "new com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto(j.id, j.name, j.fileInfo.fileUrl, j.price, j.timeApprox) " +
            "FROM Job j join j.category c on j.category.id = c.id " +
            "WHERE (:categoryId is null or c.id = :categoryId) " +
            "AND j.name LIKE :search")
    Page<JobListResDto> findAllWithSearch(String search, Long categoryId, Pageable pageable);

    @Query("SELECT " +
            "new com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto(j.id, j.name, j.fileInfo.fileUrl, j.typeJob , j.price, j.timeApprox) " +
            "FROM Job j join j.category c on j.category.id = c.id " +
            "WHERE c.id = :id")
    List<JobListResDto> getJobsByCategoryId(Long id);
}
