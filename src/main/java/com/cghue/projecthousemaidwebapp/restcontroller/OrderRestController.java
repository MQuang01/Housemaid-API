package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.OrderReqDto;
import com.cghue.projecthousemaidwebapp.service.IOrderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDto orderReqDto) throws MessagingException {
        orderService.createOrder(orderReqDto);
//        return ResponseEntity.ok().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info-order/{code}/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> getOrderByCode(@PathVariable String code, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getInfoOrder(code, id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllOrder(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderService.findAllOrder(pageable));
    }

    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> updateOrderProcess(@PathVariable Long id,
                                                @RequestParam String status) {
        orderService.editOrderProcess(id, status);
        return ResponseEntity.ok("Thay đổi trạng thái thành công");
    }
}
