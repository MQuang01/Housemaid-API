package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.RatingCategory;
import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface IRatingCategoryRepository extends JpaRepository<RatingCategory, Long> {

    @Query(value =
            "select c, r.percent from categories c " +
                    "inner join orders o on o.category_id = c.id " +
                    "inner join feed_backs f on f.order_id = o.id " +
                    "inner join rating_categories r on r.category_id = c.id" +
                    "where f.id = :feedBackId", nativeQuery = true)
    RatingCategory findRatingCategoryAndCategoryWithFeedBack(Long feedBackId);

    @Query(value =
            "select count(*) from categories c " +
                    "inner join orders o on o.category_id = c.id " +
                    "inner join feed_backs f on f.order_id = o.id " +
                    "inner join rating_categories r on r.category_id = c.id " +
                    "where o.id = :orderId", nativeQuery = true)
    Integer countFeedBackOfCategoryByOrderId(Long orderId);
}
