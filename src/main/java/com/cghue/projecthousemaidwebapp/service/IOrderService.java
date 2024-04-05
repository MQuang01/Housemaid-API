package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderEmployeeResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderResDto;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    Page<OrderResDto> findAllWithSearch(int page, int size);

    void createOrder(OrderReqDto orderReqDto) throws MessagingException;

    OrderResDto getOrderById(Long id);

    boolean cancelOrder(Long id);

    boolean updateOrder(Long id, OrderReqDto orderReqDto);

    boolean deleteOrder(Long id);

    OrderResDto getInfoOrderBy(String code);

    List<OrderResDto> findAllOrder();

    void editStatusOrderEmployee(Long id, String status);
    OrderEmployeeResDto getInfoOrder(String code, Long id);

}
