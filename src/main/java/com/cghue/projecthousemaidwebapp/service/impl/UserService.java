package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.*;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.repository.FileInfoRepository;
import com.cghue.projecthousemaidwebapp.repository.IRoleRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRepository;
import com.cghue.projecthousemaidwebapp.repository.IUserRoleRepository;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import com.cghue.projecthousemaidwebapp.utils.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final IRoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final FileInfoRepository fileInfoRepository;
    private final UploadService uploadService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

//        var userRoles = userRoleRepository.findAllByUserId(user.getId());

        var userRoles = userRoleRepository.findByUser(user);

        var customUserDetails = new CustomUserDetails(user, userRoles);

        return customUserDetails;

//        return new CustomUserDetails(user, userRoles);
    }

    @Override
    @Transactional
    public void update(Long id, RegisterReqDto userEdit) {
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

//    @Override
//    public Optional<UserRole> findByUser(User user) {
//        return userRoleRepository.findByUser(user);
//    }


    @Override
    public List<UserRole> findByUser(User user) {
        return userRoleRepository.findByUser(user);
    }

    @Transactional
    public void saveUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void register(RegisterReqDto user, MultipartFile avatar) throws IOException {
        if(userRepository.existsUsersByEmailIgnoreCaseOrPhoneOrUsernameIgnoreCase(user.email(), user.phone(), user.username())) {
            throw new IllegalArgumentException("Email, phone, or username already exists");
        }

        FileInfo fileInfo = uploadService.saveAvatar(avatar);

        User userNew = new User(
                user.fullName(),
                user.email(),
                user.address(),
                user.phone(),
                LocalDate.parse(user.dob()),
                EGender.valueOf(user.gender()),
                true,
                ETypeUser.valueOf(user.typeUser()),
                user.username(),
                passwordEncoder.encode(user.password())
        );
        userNew.setFileInfo(fileInfo);

        if (ETypeUser.valueOf(user.typeUser()).equals(ETypeUser.EMPLOYEE)) {
            if(user.shift() != null) {
                userNew.setShift(EShift.valueOf(user.shift()));
            }else {
                throw new IllegalArgumentException("Employee must have shift");
            }
        }

        userNew.setCreatedAt(LocalDate.now());

        User userCreated =  userRepository.save(userNew);
        saveUserRole(userCreated, roleRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Role not found")));
    }

    @Override
    public String login(UserLoginReqDto request) {

        UserDetails userDetails = loadUserByUsername(request.username());

        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new IllegalStateException("Wrong password or username");
        }
        UserDetailResDto userDetailResDto = getUserDetailBy(request.username());

        return jwtTokenProvider.generateToken(userDetailResDto.id(),
                userDetailResDto.fileInfoResDto().getUrl() ,
                userDetailResDto.fullName(),
                userDetailResDto.email(),
                userDetailResDto.address(),
                userDetailResDto.phone(),
                userDetailResDto.username(),
                userDetailResDto.roles());
    }

    @Override
    public UserDetailResDto getUserDetailBy(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username)).toUserDetailResDto();
    }
}
