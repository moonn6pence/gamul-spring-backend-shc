package com.gamul.gamul.api.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {

    private int status;

    private boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public DefaultResponse(final int statusCode,boolean success, final String responseMessage) {
        this.status = statusCode;
        this.success = success;
        this.message = responseMessage;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int statusCode,boolean success, final String responseMessage) {
        return res(statusCode,success, responseMessage,null);
    }

    public static<T> DefaultResponse<T> res(final int statusCode,boolean success, final String responseMessage, final T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .status(statusCode)
                .success(success)
                .message(responseMessage)
                .build();
    }
}
