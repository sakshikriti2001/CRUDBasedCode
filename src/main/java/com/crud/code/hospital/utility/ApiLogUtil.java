package com.crud.code.hospital.utility;

import com.crud.code.hospital.dto.ApiResponseCreate;
import com.crud.code.hospital.dto.ApiResponseUpdate;

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

}
