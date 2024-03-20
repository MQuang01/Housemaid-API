package com.cghue.projecthousemaidwebapp.domain;

// <<<<<<< quang/crud-order
// import com.cghue.projecthousemaidwebapp.domain.dto.res.UserResDto;
// =======
// import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
// import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
// >>>>>>> master
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
// <<<<<<< quang/crud-order

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
// =======
//     //Register Customer
//     public User(String fullName, String urlImage, String email,
//                 String address, String phone, LocalDate dob,
//                 EGender gender, Boolean isActive,
//                 String username, String password) {
//         this.fullName = fullName;
//         this.urlImage = urlImage;
//         this.email = email;
//         this.address = address;
//         this.phone = phone;
//         this.dob = dob;
//         this.gender = gender;
//         this.isActive = isActive;
//         this.username = username;
//         this.password = password;
//     }

//     public UserDetailResDto toUserDetailResDto() {
//         return new UserDetailResDto(
//                 this.id, this.fullName, this.email, this.address, this.phone, this.dob.toString(),
//                 this.gender.name(), this.username, this.password, this.urlImage,
//                 this.shift != null ? this.shift.name() : ""
//         );
//     }

//     public ListCustomerResDto toListCustomerResDto() {
//         return new ListCustomerResDto(
//                 this.id, this.fullName, this.email, this.address, this.phone, this.dob.toString(),
//                 this.gender.name(), this.username, this.urlImage
// >>>>>>> master
//         );
//     }
}
