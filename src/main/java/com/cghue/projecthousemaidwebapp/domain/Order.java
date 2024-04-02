package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String address;
    @ManyToOne
    private Category category;

    private Integer totalTimeApprox;

    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;

    private Double totalPrice;

    @Column(unique = true)
    private String currentlyCode;

    private LocalDate createdAt;

    private LocalDate workDay;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    public OrderResDto toResDto() {
        return new OrderResDto(id, user.toUserResDto(), category.getName(),address, statusOrder, totalTimeApprox.toString(), totalPrice, String.valueOf(workDay), String.valueOf(timeStart), currentlyCode, String.valueOf(createdAt));
    }

}
