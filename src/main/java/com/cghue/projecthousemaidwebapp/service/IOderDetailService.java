package com.cghue.projecthousemaidwebapp.service;

import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderDetailResDto;

import java.util.List;

public interface IOderDetailService {
    List<OrderDetailResDto> findByOrderId(Long orderId);
}
