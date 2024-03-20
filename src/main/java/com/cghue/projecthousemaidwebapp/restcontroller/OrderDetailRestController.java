package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.service.IOderDetailService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-details")
@AllArgsConstructor
public class OrderDetailRestController {
    private final IOderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailByIdOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.findByOrderId(id));
    }
}
