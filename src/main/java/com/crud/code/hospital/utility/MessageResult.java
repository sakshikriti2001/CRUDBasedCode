package com.crud.code.hospital.utility;

import com.crud.code.hospital.dto.ApiResponseCreate;
import com.crud.code.hospital.dto.ApiResponseUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResult {

    private int code;
    private String status;
    private String message;
    private Object data;

    public MessageResult(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public MessageResult(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static MessageResult apiLogSuccess(ApiResponseCreate logData) {
        return new MessageResult(0, "SUCCESS", "Patient Details added successfully..", logData);
    }

    public static MessageResult apiLogSuccess(ApiResponseUpdate data)
    {
        return new MessageResult(0, "SUCCESS", "Patient Details updated successfully..", data);
    }

}
