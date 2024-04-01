package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.JobReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
import com.cghue.projecthousemaidwebapp.service.IJobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor

public class JobRestController {
    private final IJobService iJobService;

    @GetMapping
    public ResponseEntity<Page<JobListResDto>> findAllWithSearch(@RequestParam(required = false, defaultValue = "") String search
            , @PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(iJobService.findAllWithSearch(search, pageable));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getJobsByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(iJobService.getJobsByCategoryId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(iJobService.findJobById(id));
    }

    @PostMapping
    public ResponseEntity<?> addJob(@RequestParam("avatar") MultipartFile avatar,
                                    JobReqDto jobReqDto) throws IOException {
        return ResponseEntity.ok(iJobService.addJob(jobReqDto, avatar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody JobReqDto jobReqDto, @PathVariable Long id) {
        return ResponseEntity.ok(iJobService.updateJob(id, jobReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        return ResponseEntity.ok(iJobService.deleteJob(id));
    }
}
