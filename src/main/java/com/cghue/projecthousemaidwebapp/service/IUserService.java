package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.UserRole;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserUpdateReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    void register(RegisterReqDto userNew, MultipartFile avatar) throws IOException;
    void update(Long id, UserUpdateReqDto userEdit);
    UserDetailResDto getUserDetailBy(Long id);
    Page<ListCustomerResDto> getAllUserBy(Pageable pageable, String search, ETypeUser typeUser);
    String login(UserLoginReqDto userLogin);
    UserDetailResDto getUserDetailBy(String username);

//    Optional<UserRole> findByUser(User user);
    List<UserRole> findByUser(User user);

    UserDetailResDto getUserByUsername(String username);

    void delete(Long id);
}
