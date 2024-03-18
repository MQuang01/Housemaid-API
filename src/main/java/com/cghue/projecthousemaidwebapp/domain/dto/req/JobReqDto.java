package com.cghue.projecthousemaidwebapp.domain.dto.req;

import com.cghue.projecthousemaidwebapp.domain.dto.res.CategoryResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobReqDto {
    private String name;
    private String urlImage;
    private Double price;
    private Long categoryId;
}
