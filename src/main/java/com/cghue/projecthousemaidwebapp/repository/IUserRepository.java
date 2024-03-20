package com.cghue.projecthousemaidwebapp.repository;


import com.cghue.projecthousemaidwebapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query( value =
            "Select u.* from users u where u.id not in (" +
                    "SELECT oe.employee_id FROM users u " +
                    "inner join order_employees oe on u.id = oe.employee_id " +
                    "inner join orders o on oe.order_id = o.id " +
                    "where o.status_order = 'PROCESS' " +
                    ") and u.type_user = 'EMPLOYEE' LIMIT :limit" , nativeQuery = true
    )
    List<User> findAllEmployeeFreeTime(int limit);
}
