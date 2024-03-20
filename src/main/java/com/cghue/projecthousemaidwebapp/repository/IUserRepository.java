package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE u.typeUser = :type AND u.fullName LIKE %:name%")
    Page<User> findAllUserWithSearch(Pageable pageable, @Param("name") String name, @Param("type") ETypeUser type);

    boolean existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(String email, String phone, String username);
}
