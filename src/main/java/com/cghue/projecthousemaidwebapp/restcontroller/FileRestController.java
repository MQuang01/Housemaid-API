package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.service.impl.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
public class FileRestController {
    private final UploadService uploadService;


    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        return ResponseEntity.ok(uploadService.saveAvatar(avatar));
    }
}
