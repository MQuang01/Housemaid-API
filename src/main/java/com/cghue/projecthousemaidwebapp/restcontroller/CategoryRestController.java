package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import com.cghue.projecthousemaidwebapp.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryRestController {
    private final ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(iCategoryService.getAllCategories());
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryResDto>> getAllCategoriesPage(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                                     @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(iCategoryService.getAllCategoriesPage(pageable, search));
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getAllJobsByCategoryId(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                    @RequestParam(defaultValue = "") String search,
                                                    @PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.getAllJobsByCategoryId(pageable, search, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryBy(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.getCategoryBy(id));
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestParam("avatar") MultipartFile avatar ,
                                         CategoryReqDto categoryReqDto) throws IOException {
        return ResponseEntity.ok(iCategoryService.addCategory(categoryReqDto, avatar));
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
