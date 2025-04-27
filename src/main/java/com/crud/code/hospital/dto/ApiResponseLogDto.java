package com.crud.code.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseLogDto {
    private String apiName;
    private String endpointURL;
    private Object requestBody;
    private String httpMethod;
    private long totalDuration; // in milliseconds
    private int httpStatusCode;
    private Object responseBody;
    private LocalDateTime createdDate;
    private String modifiedBy;
//    private LocalDateTime modifiedDate;
}
