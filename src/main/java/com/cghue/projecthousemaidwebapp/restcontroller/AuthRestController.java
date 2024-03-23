package com.cghue.projecthousemaidwebapp.restcontroller;

import com.cghue.projecthousemaidwebapp.domain.dto.req.user.UserLoginReqDto;
import com.cghue.projecthousemaidwebapp.domain.dto.req.user.RegisterReqDto;
import com.cghue.projecthousemaidwebapp.service.IUserService;
import com.cghue.projecthousemaidwebapp.service.impl.UploadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/auths")
@AllArgsConstructor
public class AuthRestController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid RegisterReqDto userNew,
                                          @RequestParam("avatar") MultipartFile avatar) throws IOException {

        userService.register(userNew, avatar);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginReqDto userLogin) {
        return ResponseEntity.ok(userService.login(userLogin));
    }
}
