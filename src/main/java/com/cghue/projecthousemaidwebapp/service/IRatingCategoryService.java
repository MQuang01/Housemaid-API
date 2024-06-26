package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.RatingCategory;
import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;

import java.util.List;

public interface IRatingCategoryService {

    List<RatingCategoryResDto> getAllRatingCategories();
    void calculateRatingOrder(Long categoryId);
    void addRatingCategory(Category category);
}
