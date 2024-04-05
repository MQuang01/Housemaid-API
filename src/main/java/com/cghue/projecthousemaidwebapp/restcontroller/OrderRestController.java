package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.service.IOrderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderRestController {
    private final IOrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> createOrder(@RequestBody OrderReqDto orderReqDto) throws MessagingException {
        orderService.createOrder(orderReqDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/info-order/{code}/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> getOrderByCode(@PathVariable String code, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getInfoOrder(code, id));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllOrder() {
        return ResponseEntity.ok(orderService.findAllOrder());
    }

    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> updateOrderProcess(@PathVariable Long id,
                                                @RequestParam String status) {
        orderService.editOrderProcess(id, status);
        return ResponseEntity.ok("Thay đổi trạng thái thành công");
    }
}
