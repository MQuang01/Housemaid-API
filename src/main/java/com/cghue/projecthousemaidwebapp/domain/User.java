package com.cghue.projecthousemaidwebapp.domain;

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

    private String email;

    private String address;

    private String phone;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private ETypeUser typeUser;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    private Boolean isActive;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private EShift shift;

    private LocalDate createdAt;
}
