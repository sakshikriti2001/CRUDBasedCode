package com.crud.code.hospital.dto;

import com.crud.code.hospital.enums.Role;
import lombok.Data;

@Data
public class RegisterDto {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
