package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.UserRole;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserLoginResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE u.typeUser = :type AND u.fullName LIKE %:name%")
    Page<User> findAllUserWithSearch(Pageable pageable, @Param("name") String name, @Param("type") ETypeUser type);
    boolean existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(String email, String phone, String username);
    @Query("SELECT u " +
            "FROM users u WHERE u.username = :username AND u.password = :password")
    User loginUser(@Param("username") String username, @Param("password") String password);

}
