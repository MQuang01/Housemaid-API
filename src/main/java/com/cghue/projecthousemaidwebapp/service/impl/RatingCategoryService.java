package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.FeedBack;
import com.cghue.projecthousemaidwebapp.domain.RatingCategory;
import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;
import com.cghue.projecthousemaidwebapp.repository.IFeedBackRepository;
import com.cghue.projecthousemaidwebapp.repository.IRatingCategoryRepository;
import com.cghue.projecthousemaidwebapp.service.IRatingCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingCategoryService implements IRatingCategoryService {
    private final IRatingCategoryRepository iRatingCategoryRepository;
    private final IFeedBackRepository iFeedBackRepository;


    @Override
    public List<RatingCategoryResDto> getAllRatingCategories() {
        return iRatingCategoryRepository
                .findAll()
                .stream()
                .map(RatingCategory::toResDto)
                .toList();
    }

    @Override
    public void calculateRatingOrder(Long categoryId) {
        RatingCategory ratingCategory = iRatingCategoryRepository.findRatingCategoryByCategory_Id(categoryId);
        ratingCategory.setPercent(iRatingCategoryRepository.sumRatingFeedBackWithCategoryID(categoryId)
                / iRatingCategoryRepository.countFeedBackOfCategoryByCategoryId(categoryId));
        iRatingCategoryRepository.save(ratingCategory);
    }

    @Override
    public void addRatingCategory(Category category) {
        iRatingCategoryRepository.save(new RatingCategory(category, 5F));
    }
}
