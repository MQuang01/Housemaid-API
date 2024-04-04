package com.cghue.projecthousemaidwebapp.domain.dto.res;

import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {
    private Long id;
    private UserResDto user; // người dùng
    private String categoryName;
    private String address;
    private EStatusOrder status;
    private String totalTimeApprox;
    private Double totalPrice;
    private String workDay;
    private String timeStart;
    private String currentlyCode;
    private String createdAt;
    private List<OrderDetailResDto> listOrderDetail;
}
