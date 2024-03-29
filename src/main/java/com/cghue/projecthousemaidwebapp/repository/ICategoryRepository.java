package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryListResDto(c.id, c.name, c.fileInfo.fileUrl, rc.percent)" +
            "from Category c join RatingCategory rc on c.id = rc.category.id")
    List<CategoryListResDto> findAllWithRating();

    Page<Category> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
}
