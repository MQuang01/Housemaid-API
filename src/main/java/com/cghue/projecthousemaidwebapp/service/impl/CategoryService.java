package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import com.cghue.projecthousemaidwebapp.repository.ICategoryRepository;
import com.cghue.projecthousemaidwebapp.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public List<CategoryResDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::toResDto).toList();
    }

    @Override
    public CategoryResDto addCategory(CategoryReqDto categoryReqDto) {
        Category category = new Category();
        category.setName(categoryReqDto.getName());
        category.setFileInfo((FileInfo) categoryReqDto.getAvatar());
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
            if(category.getFileInfo() != null){
                if(!category.getFileInfo().equals(categoryReqDto.getAvatar())){
                    category.setFileInfo((FileInfo) categoryReqDto.getAvatar());
                }
            }else {
                category.setFileInfo((FileInfo) categoryReqDto.getAvatar());
            }
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
