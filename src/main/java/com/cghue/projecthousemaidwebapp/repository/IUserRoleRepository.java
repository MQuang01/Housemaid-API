package com.cghue.projecthousemaidwebapp.repository;

import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.UserRole;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserRoleResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT ur " +
            "FROM UserRole ur WHERE ur.user.id = :id")
    List<UserRole> findAllByUserId(@Param("id") Long idUser);

//    Optional<UserRole> findByUser(User user);

    List<UserRole> findByUser(User user);
}
