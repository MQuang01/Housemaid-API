package com.cghue.projecthousemaidwebapp.domain.dto.res.order;

import com.cghue.projecthousemaidwebapp.domain.Job;
import com.cghue.projecthousemaidwebapp.domain.dto.res.JobResDto;
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
