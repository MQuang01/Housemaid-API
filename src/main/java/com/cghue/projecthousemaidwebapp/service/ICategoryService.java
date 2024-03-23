package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.CategoryReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    List<CategoryResDto> getAllCategories();
    CategoryResDto addCategory(CategoryReqDto categoryReqDto, MultipartFile avatar) throws IOException;
    boolean updateCategory(Long id,CategoryReqDto categoryReqDto);
    boolean deleteCategory(Long id);
}
