package com.cghue.projecthousemaidwebapp.domain;


import com.cghue.projecthousemaidwebapp.domain.dto.res.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String urlImage;

    @Column(unique = true, nullable = false)
    private String email;

    private String address;

    @Column(unique = true, nullable = false)
    private String phone;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private ETypeUser typeUser;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    private Boolean isActive;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private EShift shift;

    private LocalDate createdAt;

    public UserResDto toResDto() {
        return new UserResDto(
                id,
                fullName,
                email,
                phone,
                address,
                dob,
                typeUser,
                gender,
                isActive,
                username,
                password,
                shift,
                createdAt
        );
    }
}
