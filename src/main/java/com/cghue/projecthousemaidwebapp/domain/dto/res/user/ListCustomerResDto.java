package com.cghue.projecthousemaidwebapp.domain.dto.res.user;

import com.cghue.projecthousemaidwebapp.domain.dto.res.FileInfoResDto;

public record ListCustomerResDto(
        Long id,
        String fullName,
        String email,
        String address,
        String phone,
        String dob,
        String gender,
        String username,
        FileInfoResDto fileInfoResDto
) {
}
