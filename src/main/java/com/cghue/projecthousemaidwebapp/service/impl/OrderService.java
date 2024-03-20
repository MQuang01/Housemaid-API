package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Order;
import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.OrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import com.cghue.projecthousemaidwebapp.repository.*;
import com.cghue.projecthousemaidwebapp.service.IOrderService;
import com.cghue.projecthousemaidwebapp.utils.currentlycodeorder.CurrentlyCode;
import com.cghue.projecthousemaidwebapp.utils.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        Order order = new Order();
        order.setUser(userRepository.findById(orderReqDto.getUserId()).get());
        order.setCategory(categoryRepository.findById(orderReqDto.getCategoryId()).get());
        order.setCurrentlyCode(String.format(CurrentlyCode.CODE, LocalTime.now().format(DateTimeFormat.FORMAT)));
        float totalTimeApprox = 0f;
        double totalPrice = 0;
        for (OrderReqDto.OrderDetailReqDto orderDetailReqDto : orderReqDto.getListOrderDetail()) {
            if (orderDetailReqDto.getHouseSize() == null && orderDetailReqDto.getQuantity() == null) {
                totalTimeApprox += orderDetailReqDto.getTimeApprox();
                totalPrice += jobRepository.findById(orderDetailReqDto.getJobId()).get().getPrice();
            }
            if (orderDetailReqDto.getHouseSize() != null && orderDetailReqDto.getQuantity() == null) {
                totalTimeApprox += orderDetailReqDto.getHouseSize() * orderDetailReqDto.getTimeApprox();
                totalPrice += orderDetailReqDto.getHouseSize() * jobRepository.findById(orderDetailReqDto.getJobId()).get().getPrice();
            }
            if(orderDetailReqDto.getHouseSize() == null && orderDetailReqDto.getQuantity() != null) {
                totalTimeApprox += orderDetailReqDto.getQuantity() * orderDetailReqDto.getTimeApprox();
                totalPrice += orderDetailReqDto.getQuantity() * jobRepository.findById(orderDetailReqDto.getJobId()).get().getPrice();
            }
        }
        order.setTotalPrice(totalPrice);
        order.setTotalTimeApprox(totalTimeApprox);
        order.setWorkDay(LocalDate.parse(orderReqDto.getWorkDay()));
        order.setTimeStart(Time.valueOf(orderReqDto.getTimeStart()));

        order.setStatusOrder(EStatusOrder.WAITING);
        order.setCreatedAt(LocalDate.now());

        orderRepository.save(order);

        List<User> listEmployee = new ArrayList<>();
        List<User> listEmployeeFree = userRepository.findAllEmployeeFreeTime(orderReqDto.getQuantityEmployee());
        for (int i = 0; i < orderReqDto.getQuantityEmployee(); i++) {
            User user = listEmployeeFree.get(i);
            if(orderReqDto.getTimeStart().compareTo(String.valueOf(user.getShift().getStartTime())) >= 0
                    && orderReqDto.getTimeStart().compareTo(String.valueOf(user.getShift().getEndTime())) <= 0) {
                listEmployee.add(user);
            }
        }

        if(!handleListEmployee(order, listEmployee)) {
           throw new RuntimeException("Không thể tạo đơn");
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
