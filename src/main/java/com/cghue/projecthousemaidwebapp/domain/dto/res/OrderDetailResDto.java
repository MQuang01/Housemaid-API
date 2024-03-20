package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResDto {
    private Long id;
    private JobResDto job;
    private Double price;
    private Integer quantity;
    private Integer house_size;
}
