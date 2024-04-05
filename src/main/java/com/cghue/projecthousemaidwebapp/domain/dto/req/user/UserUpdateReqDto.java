package com.cghue.projecthousemaidwebapp.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserUpdateReqDto(
        @NotBlank(message = "Name must not be blank")
        String fullName,
        @NotBlank(message = "Email must not be blank")
        @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
        String email,
        @NotBlank(message = "Address must not be blank")
        String address,
        @NotBlank(message = "Phone must not be blank")
        @Pattern(regexp = "\\d{10,11}", message = "Phone must contain 10 to 11 digits.")
        String phone,
        @NotBlank(message = "Date of birth must not be blank")
        String dob,
        @NotBlank(message = "Gender must not be blank")
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender is incorrect")
        String gender

) {
}
