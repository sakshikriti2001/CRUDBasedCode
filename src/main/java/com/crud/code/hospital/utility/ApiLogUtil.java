package com.crud.code.hospital.utility;

import com.crud.code.hospital.dto.ApiResponseLogDto;

import java.time.LocalDateTime;

public class ApiLogUtil {
    public static ApiResponseLogDto buildLog(String apiName, String url, String method, Object requestBody, Object responseBody, long startTime, int statusCode) {
        long duration = (System.currentTimeMillis() - startTime);
        return ApiResponseLogDto.builder()
                .apiName(apiName)
                .endpointURL(url)
                .httpMethod(method)
                .requestBody(requestBody)
                .responseBody(responseBody)
                .httpStatusCode(statusCode)
                .totalDuration(duration)
                .createdDate(LocalDateTime.now())
                .modifiedBy("Sakshi jha")
                .build();
    }
}
