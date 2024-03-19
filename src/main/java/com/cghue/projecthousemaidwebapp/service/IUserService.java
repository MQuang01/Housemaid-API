package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.ListCustomerResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.user.UserDetailResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    boolean registerUser(UserReqDto userNew);
    boolean updateUser(Long id, UserReqDto userEdit);
    UserDetailResDto getUserDetailBy(Long id);
    Page<ListCustomerResDto> getAllUserBy(Pageable pageable, String search, ETypeUser typeUser);

}
