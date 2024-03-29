package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.RatingCategoryResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "rating_categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingCategory {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne
    private Category category;

    private Float percent;

    public RatingCategory(Category category, Float percent) {
        this.category = category;
        this.percent = percent;
    }

    public RatingCategoryResDto toResDto() {
        return new RatingCategoryResDto(id, category.getName(), percent);
    }
}
