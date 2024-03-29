package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import com.cghue.projecthousemaidwebapp.repository.ICategoryRepository;
import com.cghue.projecthousemaidwebapp.repository.IRatingCategoryRepository;
import com.cghue.projecthousemaidwebapp.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final IRatingCategoryRepository ratingCategoryRepository;
    private final UploadService uploadService;

    @Override
    public List<CategoryListResDto> getAllCategories() {
        return categoryRepository.findAllWithRating();
    }

    @Override
    public CategoryResDto addCategory(CategoryReqDto categoryReqDto, MultipartFile avatar) throws IOException {
        FileInfo fileInfo = uploadService.saveAvatar(avatar);
        Category category = new Category();
        category.setName(categoryReqDto.getName());
        category.setFileInfo(fileInfo);
        categoryRepository.save(category);
        return category.toResDto();
    }

    @Override
    public boolean updateCategory(Long id, CategoryReqDto categoryReqDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            if(!category.getName().equals(categoryReqDto.getName())){
                category.setName(categoryReqDto.getName());
            }
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        ratingCategoryRepository.deleteByCategoryId(id);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
