package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderEmployeeResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrderResDto> findAllWithSearch(int page, int size);

    OrderResDto createOrder(OrderReqDto orderReqDto);

    OrderResDto getOrderById(Long id);

    boolean cancelOrder(Long id);

    boolean updateOrder(Long id, OrderReqDto orderReqDto);

    boolean deleteOrder(Long id);

    OrderResDto getInfoOrder(String code);

    Page<OrderResDto> findAllOrder(Pageable pageable);

    void editOrderProcess(Long id, String status);
    OrderEmployeeResDto getInfoOrder(String code, Long id);
}
