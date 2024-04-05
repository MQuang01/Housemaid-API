package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query( value =
            "Select u.* from users u where u.id not in (" +
                    "SELECT oe.employee_id " +
                    "FROM users u " +
                    "INNER JOIN order_employees oe ON u.id = oe.employee_id " +
                    "INNER JOIN orders o ON oe.order_id = o.id " +
                    "WHERE o.status_order = 'PROCESS' " +
                    "AND o.work_day = :workDay " +
                    "AND ( " +
                    "    (:timeStart BETWEEN o.time_start AND o.time_end OR :timeEnd BETWEEN o.time_start AND o.time_end) " +
                    "    OR " +
                    "    (:timeStart <= o.time_start AND :timeEnd >= o.time_end) " +
                    ") " +
                    "OR (o.time_start < :timeStart AND o.time_end > :timeEnd)" +
                    ") " +
                    "and u.type_user = 'EMPLOYEE' " +
                    "and u.is_active = true " +
                    "ORDER BY RAND() LIMIT :limit" , nativeQuery = true
    )
    List<User> findAllEmployeeFreeTime(int limit, String timeStart, String timeEnd, String workDay);

    @Query("SELECT u FROM User u WHERE u.typeUser = :type AND u.fullName LIKE %:name%")
    Page<User> findAllUserWithSearch(Pageable pageable, @Param("name") String name, @Param("type") ETypeUser type);
    boolean existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(String email, String phone, String username);
  
    @Query("SELECT u " +
            "FROM User u WHERE u.username = :username AND u.password = :password")
    User loginUser(@Param("username") String username, @Param("password") String password);

    @Cacheable("userByUsername")
    Optional<User> findUserByUsername(String username);


}
