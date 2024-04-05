package com.cghue.projecthousemaidwebapp.domain.dto.res;

public record EmployeeOrderResDto(
        String fullName,
        String email,
        String phone,
        String urlImage,
        String gender
) {
}
