package com.crud.code.hospital.model;

import jakarta.persistence.*;
import com.crud.code.hospital.enums.Role;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String apiName;
    private String endpointURL;
    private String httpMethod;

    @Column(columnDefinition = "TEXT")
    private String requestBody;

    @Column(columnDefinition = "TEXT")
    private String responseBody;

    private int httpStatusCode;
    private long totalDuration;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private String createdBy;
}
