package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Order;
import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.OrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EShift;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import com.cghue.projecthousemaidwebapp.repository.*;
import com.cghue.projecthousemaidwebapp.service.IOrderService;
import com.cghue.projecthousemaidwebapp.utils.CurrentlyCode;
import com.cghue.projecthousemaidwebapp.utils.FormatTimeAppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    private final IJobRepository jobRepository;
    private final IOrderEmployeeRepository orderEmployeeRepository;


    @Override
    public Page<OrderResDto> findAllWithSearch(int page, int size) {
        return null;
    }

    @Override
    public OrderResDto createOrder(OrderReqDto orderReqDto) {
        User user = userRepository.findById(orderReqDto.getUserId()).get();
        List<Order> listOrder = orderRepository.findAllByUser(user);

        if(listOrder.size() > 0) {
            for (Order order : listOrder) {
                if (order.getCreatedAt().isEqual(LocalDate.parse(orderReqDto.getWorkDay()))) {
                    throw new IllegalArgumentException("Người dùng đang có đơn hàng trong ngày");
                }
            }
        }
        Order order = new Order();
        order.setUser(userRepository.findById(orderReqDto.getUserId()).get());
        order.setAddress(orderReqDto.getAddress());
        order.setCategory(categoryRepository.findById(orderReqDto.getCategoryId()).get());
//        order.setCurrentlyCode(CurrentlyCode.CODE_ORDER);
        order.setTotalPrice(orderReqDto.getTotalPrice());
        order.setTotalTimeApprox(orderReqDto.getTotalTimeApprox());
        order.setWorkDay(LocalDate.parse(orderReqDto.getWorkDay()));
        order.setTimeStart(FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()));
        order.setStatusOrder(EStatusOrder.WAITING);
        order.setCreatedAt(LocalDate.now());

        List<User> listEmployee = new ArrayList<>();
        List<User> listEmployeeFree = userRepository.findAllEmployeeFreeTime(orderReqDto.getQuantityEmployee());
        for (User employee : listEmployeeFree) {
            for (EShift shift : EShift.values()) {
                if (FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()).isAfter(shift.getStartTime()) && FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()).isBefore(shift.getEndTime())) {
                    listEmployee.add(employee);
                    break;
                }
            }
        }

        orderRepository.save(order);

        if(!handleListEmployee(order, listEmployee)) {
            throw new NoSuchElementException("Không tìm thấy nhân viên phù hợp");
        }

        return order.toResDto();
    }

    private boolean handleListEmployee(Order order, List<User> listEmployee) {
        if(listEmployee.isEmpty()) {
            return false;
        }
        createOrderEmployee(order, listEmployee);
        return true;
    }

    @Override
    public OrderResDto getOrderById(Long id) {
        return orderRepository.findById(id).get().toResDto();
    }

    @Override
    public boolean cancelOrder(Long id) {
        return false;
    }

    @Override
    public boolean updateOrder(Long id, OrderReqDto orderReqDto) {
        return false;
    }

    @Override
    public boolean deleteOrder(Long id) {
        return false;
    }

    public void createOrderEmployee(Order order, List<User> listEmployee) {
        for (User user : listEmployee) {
            OrderEmployee orderEmployee = new OrderEmployee();
            orderEmployee.setOrder(order);
            orderEmployee.setEmployee(user);
            orderEmployeeRepository.save(orderEmployee);
        }
    }
}
