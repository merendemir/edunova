package com.edunova.commons.api;

import com.edunova.commons.service.LocaleService;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

@Getter
public class ResponseHandler<T> {
    private final T data;
    private final String message;
    private final LocalDateTime timestamp;
    private final boolean isSuccess;
    private final String locale ;

    private ResponseHandler(T data, String message, boolean isSuccess) {
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isSuccess = isSuccess;
        this.locale = LocaleContextHolder.getLocale().getLanguage();
    }

    public static <T> ResponseHandler<T> success() {
        return success(null, "operation.success");
    }

    public static <T> ResponseHandler<T> success(T data) {
        return success(data, "operation.success");
    }

    public static <T> ResponseHandler<T> success(T data, String messageCode) {
        return new ResponseHandler<>(data, LocaleService.getMessage(messageCode), true);
    }

    public static <T> ResponseHandler<T> success(T data, String messageCode, String... params) {
        return new ResponseHandler<>(data, LocaleService.getMessage(messageCode, params), true);
    }

    public static <T> ResponseHandler<T> error() {
        return new ResponseHandler<>(null, "operation.failed", false);
    }

    public static <T> ResponseHandler<T> error(String messageCode) {
        return new ResponseHandler<>(null, LocaleService.getMessage(messageCode), false);
    }

    public static <T> ResponseHandler<T> error(String messageCode, String... params) {
        return new ResponseHandler<>(null, LocaleService.getMessage(messageCode, params), false);
    }

}
