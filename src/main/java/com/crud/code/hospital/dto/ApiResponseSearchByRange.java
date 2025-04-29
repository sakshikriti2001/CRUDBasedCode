package com.crud.code.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseSearchByRange {
    private String apiName;
    private String endpointURL;
    private Object requestBody;
    private String httpMethod;
    private long totalDuration; // in milliseconds
    private int httpStatusCode;
    private Object responseBody;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
}
