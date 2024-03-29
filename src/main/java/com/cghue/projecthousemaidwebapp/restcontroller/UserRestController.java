package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
    private final IUserService iUserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailUser(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.getUserDetailBy(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody RegisterReqDto userEdit) {
        iUserService.update(id, userEdit);
        return ResponseEntity.ok("User update successfully!");
    }
}
