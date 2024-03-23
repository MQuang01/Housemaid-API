package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {

    void register(RegisterReqDto userNew, MultipartFile avatar) throws IOException;
    void update(Long id, RegisterReqDto userEdit);
    UserDetailResDto getUserDetailBy(Long id);
    Page<ListCustomerResDto> getAllUserBy(Pageable pageable, String search, ETypeUser typeUser);
    String login(UserLoginReqDto userLogin);


}
