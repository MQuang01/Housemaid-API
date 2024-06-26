package com.cghue.projecthousemaidwebapp.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResDto {
    private Long id;
    private String name;
    private FileInfoResDto fileInfo;
    private Integer quantityJob;
}
