package com.cghue.projecthousemaidwebapp.domain.dto.res;

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
    private Float timeApprox;
    private String categoryName;

    public JobListResDto(Long id, String name, String urlImage, ETypeJob type, Double price, Float timeApprox, String categoryName) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.typeJob = type.getName();
        this.price = price;
        this.timeApprox = timeApprox;
        this.categoryName = categoryName;
    }
}
