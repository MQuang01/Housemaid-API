package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryListResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    List<CategoryListResDto> getAllCategories();
    Page<CategoryResDto> getAllCategoriesPage(Pageable pageable, String search);
    CategoryResDto addCategory(CategoryReqDto categoryReqDto, MultipartFile avatar) throws IOException;

    CategoryResDto getCategoryBy(Long id);
    boolean updateCategory(Long id,CategoryReqDto categoryReqDto);
    boolean deleteCategory(Long id);
}
