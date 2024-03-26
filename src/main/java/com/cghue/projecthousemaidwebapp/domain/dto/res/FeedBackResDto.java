package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserResDto;
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
    private Float percent;
    private OrderResDto order;
}
