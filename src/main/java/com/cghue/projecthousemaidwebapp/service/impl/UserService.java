package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Role;
import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.UserRole;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserLoginResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.repository.IRoleRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRoleRepository;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class UserService implements IUserService {
    protected final IUserRepository userRepository;
    protected final IUserRoleRepository userRoleRepository;
    protected final IRoleRepository roleRepository;

    @Override
    @Transactional
    public void register(UserReqDto user) {
        if(userRepository.existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(user.email(), user.phone(), user.username())) {
            throw new IllegalArgumentException("Email, phone, or username already exists");
        }

        User userNew = new User(
                user.fullName(),
                user.urlImage(),
                user.email(),
                user.address(),
                user.phone(),
                LocalDate.parse(user.dob()),
                EGender.valueOf(user.gender()),
                true,
                user.username(),
                user.password()
        );

        if(user.shift() != null) {
            userNew.setShift(EShift.valueOf(user.shift()));
            userNew.setTypeUser(ETypeUser.EMPLOYEE);
        } else {
            userNew.setTypeUser(ETypeUser.CUSTOMER);
        }

        userNew.setCreatedAt(LocalDate.now());

        User userCreated =  userRepository.save(userNew);
        saveUserRole(userCreated, roleRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Role not found")));
    }

    @Transactional
    public void saveUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void update(Long id, UserReqDto userEdit) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cannot find the user you are looking for"));

        if(!userEdit.email().equals(existingUser.getEmail())) {
            if(userRepository.existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(userEdit.email(), null, null)) {
                throw new IllegalArgumentException("Email already exists");
            }
        }
        if(!userEdit.phone().equals(existingUser.getPhone())) {
            if(userRepository.existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(null, userEdit.phone(), null)) {
                throw new IllegalArgumentException("Phone already exists");
            }
        }

        existingUser.setFullName(userEdit.fullName());
        existingUser.setUrlImage(userEdit.urlImage());
        existingUser.setEmail(userEdit.email());
        existingUser.setAddress(userEdit.address());
        existingUser.setPhone(userEdit.phone());
        existingUser.setDob(LocalDate.parse(userEdit.dob()));
        existingUser.setGender(EGender.valueOf(userEdit.gender()));
        existingUser.setUsername(userEdit.username());
        existingUser.setPassword(userEdit.password());

        if (userEdit.shift() != null) {
            existingUser.setShift(EShift.valueOf(userEdit.shift()));
            existingUser.setTypeUser(ETypeUser.EMPLOYEE);
        } else {
            existingUser.setTypeUser(ETypeUser.CUSTOMER);
            existingUser.setShift(null);
        }

        userRepository.save(existingUser);
    }

    @Override
    public UserDetailResDto getUserDetailBy(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id)).toUserDetailResDto();
    }
    @Override
    public Page<ListCustomerResDto> getAllUserBy(Pageable pageable, String search, ETypeUser typeUser) {
        return userRepository.findAllUserWithSearch(pageable, search, typeUser).map(User::toListCustomerResDto);
    }

    @Override
    public UserLoginResDto login(UserLoginReqDto user) {
        User userLogin = userRepository.loginUser(user.username(), user.password());
        if(userLogin == null) throw new IllegalArgumentException("Tên tài khoản hoặc mật khẩu bị sai");
        return new UserLoginResDto(userLogin.getId(),
                userLogin.getUsername(),
                userLogin.getFullName(),
                userLogin.getUrlImage(),
                userRoleRepository.findAllByUserId(userLogin.getId()));
    }
}
