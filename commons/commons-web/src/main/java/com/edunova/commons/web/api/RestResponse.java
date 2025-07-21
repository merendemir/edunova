package com.edunova.commons.web.api;


import com.edunova.commons.web.service.LocaleService;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

@Getter
public class RestResponse<T> {
    private final T data;
    private final String message;
    private final LocalDateTime timestamp;
    private final boolean isSuccess;
    private final String locale ;


    private RestResponse(T data, String message, boolean isSuccess) {
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isSuccess = isSuccess;
        this.locale = LocaleContextHolder.getLocale().getLanguage();
    }

    public static <T> RestResponse<T> success() {
        return success(null, "operation.success");
    }

    public static <T> RestResponse<T> success(T data) {
        return success(data, "operation.success");
    }

    public static <T> RestResponse<T> success(T data, String messageCode) {
        return new RestResponse<>(data, LocaleService.getMessage(messageCode), true);
    }

    public static <T> RestResponse<T> success(T data, String messageCode, String... params) {
        return new RestResponse<>(data, LocaleService.getMessage(messageCode, params), true);
    }

    public static <T> RestResponse<T> error() {
        return new RestResponse<>(null, "operation.failed", false);
    }

    public static <T> RestResponse<T> error(String messageCode) {
        return new RestResponse<>(null, LocaleService.getMessage(messageCode), false);
    }

    public static <T> RestResponse<T> error(String messageCode, String... params) {
        return new RestResponse<>(null, LocaleService.getMessage(messageCode, params), false);
    }

}
