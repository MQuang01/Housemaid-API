package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.OrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

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

    private Float totalTimeApprox;

    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;

    private Double totalPrice;

    @Column(unique = true)
    private String currentlyCode;

    private LocalDate createdAt;

    private LocalDate workDay;

    private Time timeStart;

    public OrderResDto toResDto() {
        return new OrderResDto(id, user.toUserResDto(), category.getName(),address, statusOrder, totalTimeApprox, totalPrice, workDay, timeStart, currentlyCode, createdAt);
    }
}
