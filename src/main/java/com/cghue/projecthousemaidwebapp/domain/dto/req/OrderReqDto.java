package com.cghue.projecthousemaidwebapp.domain.dto.req;

import com.cghue.projecthousemaidwebapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {
    private Long userId;
    private Long categoryId;
    private String address;
    private Integer totalTimeApprox;
    private Double totalPrice;
    private Integer quantityEmployee;
    private String workDay;
    private String timeStart;

    private List<OrderDetailReqDto> listOrderDetail;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailReqDto {
        private Long jobId;
        private Integer timeApprox;
        private Integer quantity;
        private Integer houseSize;
    }
}

