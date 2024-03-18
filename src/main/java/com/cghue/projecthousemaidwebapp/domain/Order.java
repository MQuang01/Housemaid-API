package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.enumeration.EHouseSize;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
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

    @ManyToOne
    private Category category;

    private Integer totalTimeApprox;

    @Enumerated(EnumType.STRING)
    private EHouseSize houseSize;

    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;

    private Double totalPrice;

    @Column(unique = true)
    private String currentlyCode;

    @OneToMany(mappedBy = "order")
    private List<User> listEmployee;

    private LocalDate createdAt;

    private LocalDate workDay;

    private Float timeStart;

}
