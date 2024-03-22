package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryRestController {
    private final ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(iCategoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestParam("avatar") MultipartFile avatar , @RequestBody CategoryReqDto categoryReqDto) {
        return ResponseEntity.ok(iCategoryService.addCategory(categoryReqDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryReqDto categoryReqDto,
                                             @PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.updateCategory(id, categoryReqDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.deleteCategory(id));
    }
}
