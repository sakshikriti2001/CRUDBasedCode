package com.crud.code.hospital.utility;

import com.crud.code.hospital.dto.ApiResponseCreate;
import com.crud.code.hospital.dto.ApiResponseSearchByRange;
import com.crud.code.hospital.dto.ApiResponseUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApiLogUtil {
    public static ApiResponseCreate buildLog(String apiName, String url, String method, Object requestBody, Object responseBody, long startTime, int statusCode) {
        long duration = (System.currentTimeMillis() - startTime);
        return ApiResponseCreate.builder()
                .apiName(apiName)
                .endpointURL(url)
                .httpMethod(method)
                .requestBody(requestBody)
                .responseBody(responseBody)
                .httpStatusCode(statusCode)
                .totalDuration(duration)
                .createdDate(LocalDateTime.now())
                .createdBy("Sakshi jha")
                .build();
    }

    public static ApiResponseUpdate buildLogForUpdate(String apiName, String url, String method, Object requestBody, Object responseBody, long startTime, int statusCode) {
        long duration = (System.currentTimeMillis() - startTime);
        return ApiResponseUpdate.builder()
                .apiName(apiName)
                .endpointURL(url)
                .httpMethod(method)
                .requestBody(requestBody)
                .responseBody(responseBody)
                .httpStatusCode(statusCode)
                .totalDuration(duration)
                .modifiedBy("Sakshi jha")
                .modifiedDate(LocalDateTime.now())
                .build();
    }

    public static ApiResponseSearchByRange buildLogForDateRange(String apiName, String url, String method, LocalDate fromDate , LocalDate toDate,Object responseBody, long startTime, int statusCode)
    {
        long duration = (System.currentTimeMillis() - startTime);
        String requestSummary = "From: " + fromDate + ", To: " + toDate;
        return ApiResponseSearchByRange.builder()
                .apiName(apiName)
                .endpointURL(url)
                .httpMethod(method)
                .requestBody(requestSummary)
                .responseBody(responseBody)
                .httpStatusCode(statusCode)
                .totalDuration(duration)
                .modifiedBy("Sakshi jha")
                .modifiedDate(LocalDateTime.now())
                .build();
    }

}
