package com.cghue.projecthousemaidwebapp.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedBackResDto {

    private Long id;
    private UserResDto user;
    private String description;
    private Integer percent;
    private OrderResDto order;
}
