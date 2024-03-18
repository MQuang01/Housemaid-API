package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryResDto> getAllCategories();
    CategoryResDto addCategory(CategoryReqDto categoryReqDto);
    boolean updateCategory(Long id,CategoryReqDto categoryReqDto);
    boolean deleteCategory(Long id);
}
