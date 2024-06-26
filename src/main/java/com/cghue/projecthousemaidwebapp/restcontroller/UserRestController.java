package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserUpdateReqDto;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
    private final IUserService iUserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailUser(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.getUserDetailBy(id));
    }

    @GetMapping("/current-user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(iUserService.getUserByUsername(username));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid UserUpdateReqDto userEdit,
                                        @RequestParam(value = "avatar", required = false) MultipartFile avatar) throws IOException {
        iUserService.update(id, userEdit, avatar);
        return ResponseEntity.ok("User update successfully!");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        iUserService.delete(id);
        return ResponseEntity.ok("User delete successfully!");
    }
}
