package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.JobReqDto;
import com.cghue.projecthousemaidwebapp.service.IJobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor

public class JobRestController {
    private final IJobService iJobService;

    @GetMapping
    public ResponseEntity<?> getAllJobs(){
        return ResponseEntity.ok(iJobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id){
        return ResponseEntity.ok(iJobService.findJobById(id));
    }

    @PostMapping
    public ResponseEntity<?> addJob(@RequestParam("avatar") MultipartFile avatar,
                                    @Valid JobReqDto jobReqDto) throws IOException {
        return ResponseEntity.ok(iJobService.addJob(jobReqDto, avatar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody JobReqDto jobReqDto, @PathVariable Long id){
        return ResponseEntity.ok(iJobService.updateJob(id, jobReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        return ResponseEntity.ok(iJobService.deleteJob(id));
    }
}
