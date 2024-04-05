package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToOne(cascade = CascadeType.REMOVE)
    private FileInfo fileInfo;

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

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<OrderEmployee> orderEmployees;
    //Register Customer
    public User(String fullName, String email,
                String address, String phone, LocalDate dob,
                EGender gender, Boolean isActive,
                ETypeUser typeUser,
                String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.isActive = isActive;
        this.typeUser = typeUser;
        this.username = username;
        this.password = password;
    }

    public UserDetailResDto toUserDetailResDto() {
        return new UserDetailResDto(
                this.id, this.fullName, this.email, this.address, this.phone, this.dob.toString(),
                this.gender.name(), this.username, this.fileInfo != null ? this.fileInfo.toResDto() : null,
                this.userRoles.stream().map(UserRole::toUserRoleResDto).collect(Collectors.toList())
        );
    }

    public ListCustomerResDto toListCustomerResDto() {
        return new ListCustomerResDto(
                this.id, this.fullName, this.email, this.address, this.phone, this.dob.toString(),
                this.gender.name(), this.username, this.fileInfo != null ? this.fileInfo.getFileUrl() : null
        );
    }

    public UserResDto toUserResDto() {
        return new UserResDto(
                id,
                fullName,
                email,
                phone,
                address,
                String.valueOf(dob),
                typeUser,
                gender,
                isActive,
                username,
                password,
                String.valueOf(createdAt),
                fileInfo != null ? fileInfo.toResDto() : null
        );
    }
}
