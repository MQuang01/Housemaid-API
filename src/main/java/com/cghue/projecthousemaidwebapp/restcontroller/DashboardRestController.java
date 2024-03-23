package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dash-boards")
@AllArgsConstructor
public class DashboardRestController {
    protected final IUserService userService;

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomer(@PageableDefault Pageable pageable,
                                              @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(userService.getAllUserBy(pageable, search, ETypeUser.CUSTOMER));
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee(@PageableDefault Pageable pageable,
                                              @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(userService.getAllUserBy(pageable, search, ETypeUser.EMPLOYEE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDetailBy(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody RegisterReqDto userEdit) {
        userService.update(id, userEdit);
        return ResponseEntity.ok("User update successfully!");
    }


}
