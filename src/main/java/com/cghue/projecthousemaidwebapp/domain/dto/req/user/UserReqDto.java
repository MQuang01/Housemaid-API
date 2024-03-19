package com.cghue.projecthousemaidwebapp.domain.dto.req.user;

public record UserReqDto(
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
)
{ }
