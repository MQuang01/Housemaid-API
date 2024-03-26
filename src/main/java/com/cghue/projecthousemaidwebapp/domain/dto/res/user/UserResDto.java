package com.cghue.projecthousemaidwebapp.domain.dto.res.user;

import com.cghue.projecthousemaidwebapp.domain.dto.res.FileInfoResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;
    private ETypeUser typeUser;
    private EGender gender;
    private Boolean isActive;
    private String username;
    private String password;
    private EShift shift;
    private LocalDate createdAt;
    private FileInfoResDto fileInfoResDto;
}
