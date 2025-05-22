package com.crud.code.hospital.service;

import com.crud.code.hospital.dto.LoginDto;
import com.crud.code.hospital.dto.RegisterDto;
import com.crud.code.hospital.enums.Role;
import com.crud.code.hospital.model.UserEntity;
import com.crud.code.hospital.repository.UserRepo;
import com.crud.code.hospital.utility.JwtUtility;
import com.crud.code.hospital.utility.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RegisterLogin {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtility jwtUtility;

    public MessageResult register(RegisterDto registerDto){

        long startTime = System.currentTimeMillis();
        Optional<UserEntity> existingUser = userRepo.findByUserName(registerDto.getUserName());
        if(existingUser.isPresent()){
            throw new RuntimeException("User already present. Please login.");
        }
        UserEntity user = new UserEntity();
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        if (registerDto.getRole() != null) {
            user.setRole(registerDto.getRole());
        } else {
            user.setRole(Role.User);
        }
        userRepo.save(user);

//        ApiResponseCreateEntity log = new ApiResponseCreateEntity();
//        log.setApiName("Register User");
//        log.setEndpointURL("/auth/registerUser");
//        log.setHttpMethod("POST");
//        log.setRequestBody(registerDto.toString());
//        log.setResponseBody("User registered successfully!");
//        log.setHttpStatusCode(200);
//        log.setTotalDuration(System.currentTimeMillis() - startTime);
//        log.setCreatedBy(user.getUserName());
//        log.setCreatedDate(LocalDateTime.now());
//        log.setUserEntity(user);
//
//        apiLogResponseRepo.save(log); // save in same DB

        return MessageResult.registerMessageSuccess("User registered successfully!");
    }

    public Map<String, String> login(LoginDto dto){
        Optional<UserEntity> existingUser = userRepo.findByUserName(dto.getUserName());
        if (existingUser.isEmpty()){
            throw new RuntimeException("please register first");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(),dto.getPassword()));
        customUserDetailsService.loadUserByUsername(dto.getUserName());
        String role = existingUser.get().getRole().name();
        String token = jwtUtility.generateToken(dto.getUserName(), role);

        return Map.of("token", token);
    }
}
