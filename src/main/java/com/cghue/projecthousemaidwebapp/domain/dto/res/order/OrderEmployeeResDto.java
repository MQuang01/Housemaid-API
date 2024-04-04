package com.cghue.projecthousemaidwebapp.domain.dto.res.order;

import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderEmployeeResDto {
    private Long id;
    private String user; // người dùng
    private String orderName;
    private String address;
    private Integer totalTimeApprox;
    private Double total;
    private String workDay;
    private String timeStart;
    private String currentlyCode;
    private String createdAt;
    private Long quantityEmployee;

    // constructor

    public OrderEmployeeResDto(Long id, String user, String orderName, String address, Integer totalTimeApprox, Double total, LocalDate workDay, LocalTime timeStart, String currentlyCode, LocalDate createdAt, Long quantityEmployee) {
        this.id = id;
        this.user = user;
        this.orderName = orderName;
        this.address = address;
        this.totalTimeApprox = totalTimeApprox;
        this.total = total;
        this.workDay = workDay.toString();
        this.timeStart = timeStart.toString();
        this.currentlyCode = currentlyCode;
        this.createdAt = createdAt.toString();
        this.quantityEmployee = quantityEmployee;
    }
}
