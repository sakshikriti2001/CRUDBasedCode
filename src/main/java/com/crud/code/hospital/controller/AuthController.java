package com.crud.code.hospital.controller;

import com.crud.code.hospital.dto.LoginDto;
import com.crud.code.hospital.dto.RegisterDto;
import com.crud.code.hospital.service.RegisterLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    RegisterLogin registerLogin;

    @PostMapping("/registerUser")
    public String register(@RequestBody RegisterDto dto){
   registerLogin.register(dto);
   return "User registered successfully!";
    }
    @PostMapping("/loginUser")
    public Map<String, String> login(@RequestBody LoginDto dto){
        return registerLogin.login(dto);
    }



}
