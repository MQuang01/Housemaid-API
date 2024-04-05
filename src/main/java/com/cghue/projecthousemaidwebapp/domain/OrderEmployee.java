package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.EmployeeOrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrderEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEmployee {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private EStatusOrderEmployee status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    public EmployeeOrderResDto employeeOrderResDto() {
        return new EmployeeOrderResDto(employee.getFullName(), employee.getEmail(), employee.getPhone(), employee.getFileInfo().getFileUrl(), employee.getGender().toString());
    }
}
