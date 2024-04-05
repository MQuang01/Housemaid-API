package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import com.cghue.projecthousemaidwebapp.domain.dto.res.EmployeeOrderResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderEmployeeRepository extends JpaRepository<OrderEmployee, Long> {

    @Query("SELECT oe " +
            "FROM OrderEmployee oe " +
            "WHERE oe.order.id = :id")
    List<OrderEmployee> findByOrderId(@Param("id") Long id);
}
