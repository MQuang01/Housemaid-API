package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.OrderDetail;
import com.cghue.projecthousemaidwebapp.domain.dto.res.OrderDetailResDto;
import com.cghue.projecthousemaidwebapp.repository.IOrderDetailRepository;
import com.cghue.projecthousemaidwebapp.service.IOderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailService implements IOderDetailService {
    private final IOrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailResDto> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId).stream().map(OrderDetail::toResDto).toList();
    }
}
