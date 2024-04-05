package com.cghue.projecthousemaidwebapp.domain.dto.res.user;

import com.cghue.projecthousemaidwebapp.domain.dto.res.FileInfoResDto;

import java.util.List;

public record UserDetailResDto(
        Long id,
        String fullName,
        String email,
        String address,
        String phone,
        String dob,
        String gender,
        String username,
        FileInfoResDto fileInfoResDto,
        List<UserRoleResDto> roles

) {
}
