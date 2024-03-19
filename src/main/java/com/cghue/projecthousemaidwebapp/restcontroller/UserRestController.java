package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserReqDto;
import com.cghue.projecthousemaidwebapp.domain.enumeration.ETypeUser;
import com.cghue.projecthousemaidwebapp.service.IUserService;
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
    public ResponseEntity<Boolean> registerUser(@RequestBody UserReqDto userNew) {
        return ResponseEntity.ok(userService.registerUser(userNew));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id,
                                              @RequestBody UserReqDto userEdit) {
        return ResponseEntity.ok(userService.updateUser(id, userEdit));
    }
}
