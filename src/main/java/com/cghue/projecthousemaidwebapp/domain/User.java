package com.cghue.projecthousemaidwebapp.domain;

import com.cghue.projecthousemaidwebapp.domain.dto.res.UserResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
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

    @OneToOne
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

    @Enumerated(EnumType.STRING)
    private EShift shift;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

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
                this.gender.name(), this.username, this.password, this.fileInfo.toResDto() != null ? this.fileInfo.toResDto() : null,
                this.shift != null ? this.shift.name() : ""
        );
    }

    public ListCustomerResDto toListCustomerResDto() {
        return new ListCustomerResDto(
                this.id, this.fullName, this.email, this.address, this.phone, this.dob.toString(),
                this.gender.name(), this.username, this.fileInfo.toResDto() != null ? this.fileInfo.toResDto() : null
        );
    }

    public UserResDto toUserResDto() {
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
                shift != null ? shift: null,
                createdAt,
                fileInfo != null ? fileInfo.toResDto() : null
        );
    }
}
