package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {


}
