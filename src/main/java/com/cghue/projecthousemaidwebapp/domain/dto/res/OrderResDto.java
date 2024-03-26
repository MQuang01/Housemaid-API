package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {
    private Long id;
    private UserResDto user; // người dùng
    private String orderName;
    private String address;
    private EStatusOrder status;
    private Float totalTimeApprox;
    private Double total;
    private LocalDate workDay;
    private Time timeStart;
    private String currentlyCode;
    private LocalDate createdAt;
}
