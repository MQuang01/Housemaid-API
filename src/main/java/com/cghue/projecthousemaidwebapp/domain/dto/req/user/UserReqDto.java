package com.cghue.projecthousemaidwebapp.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserReqDto(
        @NotBlank(message = "Name must not be blank")
        @Pattern(regexp = "^[\\p{L}\\s]{1,}[\\.]{0,1}[\\p{L}\\s]{0,}$", message = "Invalid name")
        String fullName,
        @NotBlank(message = "Email must not be blank")
        @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
        String email,
        @NotBlank(message = "Address must not be blank")
        String address,
        @NotBlank(message = "Phone must not be blank")
        @Pattern(regexp = "\\d{10,12}", message = "Phone must contain 10 to 12 digits.")
        String phone,
        @NotBlank(message = "Date of birth must not be blank")
        String dob,
        @NotBlank(message = "Gender must not be blank")
        @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender is incorrect")
        String gender,
        @NotBlank(message = "Username must not be blank")
        @Pattern(regexp = "^[A-Za-z0-9_]{6,20}$", message = "Username must be between 6 and 20 characters long and contain only alphanumeric characters, numbers, or underscores.")
        String username,
        @NotBlank(message = "Password must not be blank")
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and at least 6 characters.")
        String password,
        String urlImage,
        @Pattern(regexp = "^(SHIFT_1|SHIFT_2|SHIFT_3|SHIFT_4|SHIFT_5)$", message = "Shift is incorrect")
        String shift
)
{ }
