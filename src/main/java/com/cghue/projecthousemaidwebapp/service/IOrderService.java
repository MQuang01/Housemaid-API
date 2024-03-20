package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.OrderResDto;
import org.springframework.data.domain.Page;

public interface IOrderService {
    Page<OrderResDto> findAllWithSearch(int page, int size);

    OrderResDto createOrder(OrderReqDto orderReqDto);

    OrderResDto getOrderById(Long id);

    boolean cancelOrder(Long id);

    boolean updateOrder(Long id, OrderReqDto orderReqDto);

    boolean deleteOrder(Long id);
}
