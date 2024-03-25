package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.FileInfo;
import lombok.*;

@Getter
@Setter
public class JobListResDto{
    private Long id;
    private String name;
    private String urlImage;
    private Double price;
    private Float timeApprox;

    public JobListResDto(Long id, String name, String urlImage, Double price, Float timeApprox) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.price = price;
        this.timeApprox = timeApprox;
    }
}
