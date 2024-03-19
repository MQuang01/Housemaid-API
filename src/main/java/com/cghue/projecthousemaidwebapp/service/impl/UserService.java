package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EGender;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.repository.IUserRepository;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    protected final IUserRepository userRepository;

    @Override
    public boolean registerUser(UserReqDto user) {
        try {
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

            userRepository.save(userNew);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
    @Override
    public boolean updateUser(Long id, UserReqDto userEdit) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();

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
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
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
}
