package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.Category;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeJob;
import lombok.*;

@Getter
@Setter
public class JobListResDto{
    private Long id;
    private String name;
    private String urlImage;
    private String typeJob;
    private Double price;
    private Integer timeApprox;
    private CategoryResDto category;

    public JobListResDto(Long id, String name, String urlImage, ETypeJob type, Double price, Integer timeApprox, Category category) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.typeJob = type.getName();
        this.price = price;
        this.timeApprox = timeApprox;
        this.category = category.toResDto();
    }


}
