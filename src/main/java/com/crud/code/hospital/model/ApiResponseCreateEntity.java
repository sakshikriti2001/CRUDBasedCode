//package com.crud.code.hospital.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "api_log")
//public class ApiResponseCreateEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private String apiName;
//    private String endpointURL;
//    private Object requestBody;
//    private String httpMethod;
//    private long totalDuration; // in milliseconds
//    private int httpStatusCode;
//    private Object responseBody;
//    private LocalDateTime createdDate;
//    private String createdBy;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//
//}
