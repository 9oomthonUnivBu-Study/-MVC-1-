package com.example.membership.controller;


import com.example.membership.dto.LoginReqquestDto;
import com.example.membership.dto.UserDto;
import com.example.membership.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping
    public String registerUser(@RequestBody @Valid UserDto userDto) {
        Long savedId = userService.saveUser(userDto);
        return "회원가입 성공! ID: " + savedId;

    }

    // 로그인
    @PostMapping("/login")
    public String loginUser(@RequestBody @Valid LoginReqquestDto loginDto){
        return userService.loginUser(loginDto);
    }



}
