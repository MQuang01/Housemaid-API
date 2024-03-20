package com.cghue.projecthousemaidwebapp.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingCategoryResDto {
    private Long id;
    private String name;
    private Float percent;
}
