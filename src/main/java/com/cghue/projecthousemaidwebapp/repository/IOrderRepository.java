package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.Order;
import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderEmployeeResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    @Query("select new com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderEmployeeResDto" +
            "(o.id, u.fullName, c.name, o.address, o.totalTimeApprox, o.totalPrice, o.workDay, o.timeStart, o.currentlyCode, o.createdAt, count(oe.employee.id)) " +
            "from OrderEmployee oe " +
            "join Order o on o.id = oe.order.id " +
            "join Category c on o.category.id = c.id " +
            "join User u on o.user.id = u.id " +
            "where o.currentlyCode = :extractedCode " +
            "and u.id = :id " +
            "GROUP BY o")
    OrderEmployeeResDto findByCurrentlyCode(String extractedCode, Long id);


}
