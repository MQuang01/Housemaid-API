package com.cghue.projecthousemaidwebapp.domain.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReqDto {
    private String name;
    private String urlImage;
}
