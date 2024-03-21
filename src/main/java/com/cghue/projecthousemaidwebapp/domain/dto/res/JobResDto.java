package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.domain.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResDto {
    private Long id;
    private String name;
    private FileInfoResDto fileInfo;
    private Double price;
    private Float timeApprox;
    private CategoryResDto category;

}
