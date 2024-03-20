package com.cghue.projecthousemaidwebapp.domain.dto.res.user;

import com.cghue.projecthousemaidwebapp.domain.Role;

import java.util.List;

public record UserLoginResDto (
        Long id,
        String username,
        String fullName,
        String urlImage,
        List<UserRoleResDto> roles
){
}
