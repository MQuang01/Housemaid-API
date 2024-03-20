package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderEmployeeRepository extends JpaRepository<OrderEmployee, Long> {
}
