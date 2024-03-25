package com.cghue.projecthousemaidwebapp.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryListResDto {
    private Long id;
    private String name;
    private String url;
    private Float averageRating;
}
