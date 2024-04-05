package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import com.cghue.projecthousemaidwebapp.domain.dto.res.EmployeeOrderResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderEmployeeRepository extends JpaRepository<OrderEmployee, Long> {
}
