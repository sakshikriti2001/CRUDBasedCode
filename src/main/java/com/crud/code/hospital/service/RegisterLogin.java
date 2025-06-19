package com.crud.code.hospital.service;

import com.crud.code.hospital.dto.LoginDto;
import com.crud.code.hospital.dto.RegisterDto;
import com.crud.code.hospital.enums.Role;
import com.crud.code.hospital.model.UserEntity;
import com.crud.code.hospital.repository.UserRepo;
import com.crud.code.hospital.utility.JwtUtility;
import com.crud.code.hospital.utility.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegisterLogin {
    Logger logger = LoggerFactory.getLogger(RegisterLogin.class);
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

    public MessageResult register(RegisterDto registerDto) {

        long startTime = System.currentTimeMillis();
        try {
            Optional<UserEntity> existingUser = userRepo.findByUserName(registerDto.getUserName());
            if (existingUser.isPresent()) {
                logger.error("User already present ");
                return new MessageResult(1, "Error", "User already present. Please login.");
            }
            UserEntity user = new UserEntity();
            user.setUserName(registerDto.getUserName());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setApiName("Register User");
            user.setEndpointURL("/auth/registerUser");
            user.setHttpMethod("POST");
            user.setRequestBody(registerDto.toString());
            user.setResponseBody("User registered successfully!");
            user.setHttpStatusCode(200);
            user.setTotalDuration(System.currentTimeMillis() - startTime);
            user.setCreatedBy(user.getUserName());
            user.setCreatedDate(LocalDateTime.now());
            if (registerDto.getRole() != null) {
                user.setRole(registerDto.getRole());
            } else {
                user.setRole(Role.USER);
            }
            userRepo.save(user);

            return MessageResult.registerMessageSuccess("User registered successfully!");
        } catch (Exception ec) {
            ec.printStackTrace();
            return new MessageResult(1, "ERROR", "Registration failed: " + ec.getMessage());
        }
    }

    public Map<String, String> login(LoginDto dto){

            UserEntity existingUser = userRepo.findByUserName(dto.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found. Please register first."));
            if (!passwordEncoder.matches(dto.getPassword(), existingUser.getPassword())) {
                return (Map<String, String>) new MessageResult(1, "ERROR", "Invalid password");
            }
            try {

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException("Invalid userName or password");
            }
            //generate jwt token
            HashMap<String, Object> maping = new HashMap<>();
            maping.put("username", dto.getUserName());
            maping.put("UserId", existingUser.getUserId());
            String role = existingUser.getRole().name();
            maping.put("roles", role);


            String token = jwtUtility.generateToken(maping, "subject_generate");

            return Map.of("token", token,
                    "username", existingUser.getUserName(),
                    "role", role);
        }
}
