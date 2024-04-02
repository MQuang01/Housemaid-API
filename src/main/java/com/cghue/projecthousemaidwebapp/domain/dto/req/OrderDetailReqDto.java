package com.cghue.projecthousemaidwebapp.domain.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailReqDto {
    private Long id;
    private String name;
    private Integer timeApprox;
    private Integer quantity;
    private Integer houseSize;
}
