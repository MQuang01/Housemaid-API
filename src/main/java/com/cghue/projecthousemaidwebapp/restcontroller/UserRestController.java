package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserReqDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserReqDto userNew) {
        userService.register(userNew);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserReqDto userEdit) {
        userService.update(id, userEdit);
        return ResponseEntity.ok("User update successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginReqDto userLogin) {
        return ResponseEntity.ok(userService.login(userLogin));
    }
}
