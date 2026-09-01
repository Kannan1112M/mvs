package com.mvs.controller;

import com.mvs.dto.ChangePasswordReq;
import com.mvs.dto.LoginRequest;
import com.mvs.dto.UserReqDto;
import com.mvs.service.UserService;
import com.mvs.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserReqDto userReqDto){
        return userService.create(userReqDto);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody LoginRequest request){
        return userService.forgotPassword(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordReq request){
        return userService.changePassword(request);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoles(){
        return userService.getRoles();
    }


}
