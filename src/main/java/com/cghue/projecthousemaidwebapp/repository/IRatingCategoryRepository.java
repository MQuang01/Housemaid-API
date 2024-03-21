package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.RatingCategory;
import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface IRatingCategoryRepository extends JpaRepository<RatingCategory, Long> {
    RatingCategory findRatingCategoryByCategory_Id(Long categoryId);
    @Query(value =
            "select count(*) from orders o\n" +
                    "inner join feed_backs f on f.order_id = o.id\n" +
                    "where o.category_id = :categoryId and o.status_order = 'COMPLETE';", nativeQuery = true)
    Long countFeedBackOfCategoryByCategoryId(Long categoryId);

    @Query(value = "select sum(f.percent) from orders o\n" +
            "inner join feed_backs f on f.order_id = o.id\n" +
            "where o.category_id = :categoryId and o.status_order = 'COMPLETE';", nativeQuery = true)
    Float sumRatingFeedBackWithCategoryID(Long categoryId);
}
