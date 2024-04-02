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
import com.cghue.projecthousemaidwebapp.utils.AppConstant;
import com.cghue.projecthousemaidwebapp.utils.FormatTimeAppUtil;
import com.cghue.projecthousemaidwebapp.utils.SendEmail;
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
    private final EmailService emailService;


    @Override
    public Page<OrderResDto> findAllWithSearch(int page, int size) {
        return null;
    }

    @Override
    public OrderResDto createOrder(OrderReqDto orderReqDto) {
        User user = userRepository.findById(orderReqDto.getUserId()).get();
        List<Order> listOrder = orderRepository.findAllByUser(user);

        if (!listOrder.isEmpty()) {
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
        String code = String.format(AppConstant.get(), FormatTimeAppUtil.getStringFormatTime());
        order.setCurrentlyCode(code);
        order.setTotalPrice(orderReqDto.getTotalPrice());
        order.setTotalTimeApprox(orderReqDto.getTotalTimeApprox());
        order.setWorkDay(LocalDate.parse(orderReqDto.getWorkDay()));
        order.setTimeStart(FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()));
        order.setStatusOrder(EStatusOrder.WAITING);
        order.setCreatedAt(LocalDate.now());

        String shiftReq = "";
        for (EShift shift : EShift.values()) {
            if (FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()).isAfter(shift.getStartTime())
                    && FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getTimeStart()).isBefore(shift.getEndTime())) {
                shiftReq = shift.toString();
                break;
            }
        }

        List<User> listEmployeeFree = userRepository.findAllEmployeeFreeTime(orderReqDto.getQuantityEmployee(), shiftReq);


        if (listEmployeeFree.size() < orderReqDto.getQuantityEmployee()) {
            throw new IllegalArgumentException("Không đủ nhân viên yêu cầu");
        }

        orderRepository.save(order);

//        createOrderEmployee(order, listEmployeeFree); khoan lưu lại đợi xác nhận mail

        emailService.sendEmail(user.getEmail(), "THÔNG TIN ĐẶT LỊCH CỦA BẠN", SendEmail.EmailScheduledSuccessfully(user.getUsername(), order.getWorkDay().toString(), order.getTimeStart().toString(), String.format(AppConstant.getUrlConfirmOrder(), code), AppConstant.getSignature()));

        return order.toResDto();
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

    @Override
    public OrderResDto getInfoOrder(String code) {
        String[] parts = code.split("\\$");
        String extractedCode = parts[1];
        return orderRepository.findByCurrentlyCode(extractedCode).toResDto();
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
