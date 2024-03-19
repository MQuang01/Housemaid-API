package com.cghue.projecthousemaidwebapp.domain.dto.res.user;

public record UserDetailResDto(
        Long id,
        String fullName,
        String email,
        String address,
        String phone,
        String dob,
        String gender,
        String username,
        String password,
        String urlImage,
        String shift

) {
}
