package com.cghue.projecthousemaidwebapp.service.impl;

import com.cghue.projecthousemaidwebapp.domain.Order;
import com.cghue.projecthousemaidwebapp.domain.OrderDetail;
import com.cghue.projecthousemaidwebapp.domain.OrderEmployee;
import com.cghue.projecthousemaidwebapp.domain.User;
import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderDetailReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderEmployeeResDto;
import com.cghue.projecthousemaidwebapp.domain.dto.res.order.OrderResDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrder;
import com.cghue.projecthousemaidwebapp.domain.enumeration.EStatusOrderEmployee;
import com.cghue.projecthousemaidwebapp.repository.*;
import com.cghue.projecthousemaidwebapp.service.IOrderService;
import com.cghue.projecthousemaidwebapp.utils.AppConstant;
import com.cghue.projecthousemaidwebapp.utils.FormatTimeAppUtil;
import com.cghue.projecthousemaidwebapp.utils.SendEmail;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    private final IJobRepository jobRepository;
    private final IOrderEmployeeRepository orderEmployeeRepository;
    private final EmailService emailService;
    private final IOrderDetailRepository orderDetailRepository;


    @Override
    public Page<OrderResDto> findAllWithSearch(int page, int size) {
        return null;
    }

    @Override
    public void createOrder(OrderReqDto orderReqDto) {
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
        order.setTimeStart(FormatTimeAppUtil.TO_LOCALTIME(orderReqDto.getStartTime()));
        order.setTimeEnd(LocalTime.parse(orderReqDto.getEndTime()));
        order.setStatusOrder(EStatusOrder.WAITING);
        order.setCreatedAt(LocalDate.now());



        List<User> listEmployeeFree = userRepository.findAllEmployeeFreeTime(orderReqDto.getQuantityEmployee(), orderReqDto.getStartTime(), orderReqDto.getEndTime(), orderReqDto.getWorkDay());

        if (listEmployeeFree.size() < orderReqDto.getQuantityEmployee()) {
            throw new IllegalArgumentException("Không đủ nhân viên yêu cầu");
        }

        orderRepository.save(order);
        createOrderEmployee(order, listEmployeeFree); //khoan lưu lại đợi xác nhận mail
        orderReqDto.getListOrderDetail().forEach(
                orderDetailReqDto -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setHouseSize(orderDetailReqDto.getHouseSize());
                    orderDetail.setQuantity(orderDetailReqDto.getQuantity());
                    orderDetail.setPrice(jobRepository.findById(orderDetailReqDto.getId()).get().getPrice());
                    orderDetail.setJob(jobRepository.findById(orderDetailReqDto.getId()).get());
                    orderDetailRepository.save(orderDetail);
                }
        );

        StringBuilder htmlContentBuilder = new StringBuilder();
        htmlContentBuilder.append("<!DOCTYPE html>");
        htmlContentBuilder.append("<html lang='en'>");
        htmlContentBuilder.append("<head>");
        htmlContentBuilder.append("<meta charset='UTF-8'>"); // Thêm thẻ meta để chỉ định mã ký tự UTF-8
        htmlContentBuilder.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        htmlContentBuilder.append("<title>Danh sách Đơn hàng</title>");
        htmlContentBuilder.append("</head>");
        htmlContentBuilder.append("<body>");
        htmlContentBuilder.append("<h1>Danh sách đơn hàng</h1>");
        htmlContentBuilder.append("<table class='table'>");
        htmlContentBuilder.append("<thead class='table-dark'>");
        htmlContentBuilder.append("<tr>");
        htmlContentBuilder.append("<th scope='col'>STT</th>");
        htmlContentBuilder.append("<th scope='col'>Tên</th>");
        htmlContentBuilder.append("<th scope='col'>Số lượng</th>");
        htmlContentBuilder.append("<th scope='col'>Đơn giá</th>");
        htmlContentBuilder.append("<th scope='col'>Thành tiền</th>");
        htmlContentBuilder.append("</tr>");
        htmlContentBuilder.append("</thead>");
        htmlContentBuilder.append("<tbody>");

        int stt = 1;

        for (OrderDetailReqDto item : orderReqDto.getListOrderDetail()) {
            double totalPriceRow = 0;
            if (item.getQuantity() == null) {
                totalPriceRow += item.getHouseSize() * jobRepository.findById(item.getId()).get().getPrice();
            } else {
                totalPriceRow += item.getQuantity() * jobRepository.findById(item.getId()).get().getPrice();
            }


            htmlContentBuilder.append("<tr>");
            htmlContentBuilder.append("<td>").append(stt).append("</td>");
            htmlContentBuilder.append("<td>").append(jobRepository.findById(item.getId()).get().getName()).append("</td>");
            htmlContentBuilder.append("<td>").append(item.getQuantity() == null ? item.getHouseSize() : item.getQuantity()).append(item.getQuantity() == null ? "m2" : " cái").append("</td>");
            htmlContentBuilder.append("<td>").append(jobRepository.findById(item.getId()).get().getPrice()).append("</td>");
            htmlContentBuilder.append("<td>").append(totalPriceRow).append("</td>");
            htmlContentBuilder.append("</tr>");

            stt++;
        }

        htmlContentBuilder.append("<tfoot>");
        htmlContentBuilder.append("<td colspan='4'>Tổng tiền</td>");
        htmlContentBuilder.append("<td>").append(orderReqDto.getTotalPrice()).append("</td>");
        htmlContentBuilder.append("</tfoot>");

        htmlContentBuilder.append("</tbody>");
        htmlContentBuilder.append("</table>");
        htmlContentBuilder.append("</body>");
        htmlContentBuilder.append("</html>");

        try {
            emailService.sendEmail(user.getEmail(), "THÔNG TIN ĐẶT LỊCH CỦA BẠN", SendEmail.EmailScheduledSuccessfully(user.getUsername(), order.getWorkDay().toString(), order.getTimeStart().toString(), String.format(AppConstant.getUrlConfirmOrder(), code, orderReqDto.getUserId()), AppConstant.getSignature()), String.valueOf(htmlContentBuilder));

        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
        orderRepository.deleteById(id);
        return true;
    }

    @Override
    public OrderResDto getInfoOrderBy(String code) {
        return null;
    }

    @Override
    public List<OrderResDto> findAllOrder() {
        return orderRepository.findAll().stream().map(Order::toResDto).collect(Collectors.toList());
    }

    @Override
    public OrderEmployeeResDto getInfoOrder(String code, Long id) {
        String[] parts = code.split("\\$");
        String extractedCode = parts[1];
        return orderRepository.findByCurrentlyCode(extractedCode, id);
    }


    @Override
    public void editStatusOrderEmployee(Long id, String status) {
        Order orderEdit = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy order theo id:" + id));
        switch (status) {
            case "WAITING" -> orderEdit.setStatusOrder(EStatusOrder.WAITING);
            case "COMPLETE" -> {
                orderEdit.setStatusOrder(EStatusOrder.COMPLETE);
            }
            case "PROCESS" -> {
                orderEdit.setStatusOrder(EStatusOrder.PROCESS);
                orderEdit.getListEmployee().forEach(orderEmployee -> {
                    orderEmployee.setStatus(EStatusOrderEmployee.CONFIRM);
                    orderEmployeeRepository.save(orderEmployee);
                    emailService.sendEmailSimple(orderEdit.getUser().getEmail(), "ĐẶT LỊCH THÀNH CÔNG", SendEmail.ExamScheduleReminderConfirm(orderEdit.getUser().getUsername(), orderEdit.getWorkDay().toString(), orderEdit.getTimeStart().toString(), AppConstant.getSignature()));
                });
            }
            case "CANCEL" -> {
                orderEdit.setStatusOrder(EStatusOrder.CANCEL);
                orderEdit.getListEmployee().forEach(orderEmployee -> {
                    orderEmployee.setStatus(EStatusOrderEmployee.BUSY);
                    orderEmployeeRepository.save(orderEmployee);
                });
                emailService.sendEmailSimple(orderEdit.getUser().getEmail(), "HỦY YÊU CẦU ĐẶT LỊCH", SendEmail.ExamScheduleReminderReject(orderEdit.getUser().getUsername(), AppConstant.getSignature()));
            }
        }

        orderRepository.save(orderEdit);
    }


    public void createOrderEmployee(Order order, List<User> listEmployee) {
        for (User user : listEmployee) {
            OrderEmployee orderEmployee = new OrderEmployee();
            orderEmployee.setOrder(order);
            orderEmployee.setEmployee(user);
            orderEmployee.setStatus(EStatusOrderEmployee.WAITING);
            orderEmployeeRepository.save(orderEmployee);
        }
    }

//    public void createOrderDetails(Order order, List<OrderDetailReqDto> listOrderDetail ){
//
//        for (OrderDetailReqDto orderDetailReqDto : listOrderDetail) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(order);
//            orderDetail.setJob(jobRepository.findById(orderDetailReqDto.getId()).get());
//            orderDetail.setQuantity(orderDetailReqDto.getQuantity());
//            orderDetail.setPrice(orderDetailReqDto.getPrice());
//            orderDetailRepository.save(orderDetail);
//        }
//    }

}
