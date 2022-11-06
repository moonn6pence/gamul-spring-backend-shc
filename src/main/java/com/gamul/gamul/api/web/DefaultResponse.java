package com.gamul.gamul.api.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {

    private int statusCode;

    private boolean success;
    private String responseMessage;
    private T data;

    public DefaultResponse(final int statusCode,boolean success, final String responseMessage) {
        this.statusCode = statusCode;
        this.success = success;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int statusCode,boolean success, final String responseMessage) {
        return res(statusCode,success, responseMessage, null);
    }

    public static<T> DefaultResponse<T> res(final int statusCode,boolean success, final String responseMessage, final T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .success(success)
                .responseMessage(responseMessage)
                .build();
    }
}
