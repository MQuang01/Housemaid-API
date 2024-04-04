package com.cghue.projecthousemaidwebapp.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public record RegisterReqDto(
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
        String gender,
        @NotBlank(message = "Type user must not be blank")
        @Pattern(regexp = "^(CUSTOMER|EMPLOYEE)$", message = "Type user is incorrect")
        String typeUser,

        @NotBlank(message = "Username must not be blank")
        @Pattern(regexp = "^[a-z0-9]{6,20}$", message = "Username must be between 6 and 20 characters long and contain only alphanumeric characters, or numbers.")
        String username,
        @NotBlank(message = "Password must not be blank")
//        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and at least 6 characters.")
        String password
)
{ }
//{
//        "fullname": "Hiep Nguyen",
//        "email": "hiep@gmail.com",
//        "address": "TP Thua Thien Hue",
//        "phone": "03555625012",
//        "dob": "2000-01-13",
//        "gender": "MALE",
//        "typeUser": "CUSTOMER",
//        "username": "hiep123",
//        "password": "123123"
//        }