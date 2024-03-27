package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeJob;
import lombok.*;

@Getter
@Setter
public class JobListResDto{
    private Long id;
    private String name;
    private String urlImage;
    private String type;
    private Double price;
    private Float timeApprox;

    public JobListResDto(Long id, String name, String urlImage, Double price, Float timeApprox) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.price = price;
        this.timeApprox = timeApprox;
    }

    public JobListResDto(Long id, String name, String urlImage, ETypeJob type, Double price, Float timeApprox) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.type = type.getName();
        this.price = price;
        this.timeApprox = timeApprox;
    }
}
