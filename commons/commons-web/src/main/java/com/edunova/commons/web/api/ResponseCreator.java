package com.edunova.commons.web.api;

import com.edunova.commons.web.service.LocaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCreator<T> {

    private static <T> ResponseEntity<RestResponse<T>> success(HttpStatus status, T data, String messageCode, String... params) {
        return ResponseEntity.status(status).body(RestResponse.success(data, messageCode, params));
    }

    private static <T> ResponseEntity<RestResponse<T>> error(HttpStatus status, String messageCode, String... params) {
        return ResponseEntity.status(status).body(RestResponse.error(messageCode, params));
    }

    public static <T> ResponseEntity<RestResponse<T>> ok() {
        return success(HttpStatus.OK, null, "operation.success");
    }

    public static <T> ResponseEntity<RestResponse<T>> ok(T data) {
        return success(HttpStatus.OK, data, "operation.success");
    }

    public static <T> ResponseEntity<RestResponse<T>> ok(T data, String messageCode, String... params) {
        return success(HttpStatus.OK, data, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> created() {
        return success(HttpStatus.CREATED, null, "operation.created");
    }

    public static <T> ResponseEntity<RestResponse<T>> created(T data) {
        return success(HttpStatus.CREATED, data, "operation.created");
    }

    public static <T> ResponseEntity<RestResponse<T>> created(T data, String messageCode) {
        return success(HttpStatus.CREATED, data, messageCode);
    }

    public static <T> ResponseEntity<RestResponse<T>> created(T data, String messageCode, String... params) {
        return success(HttpStatus.CREATED, data, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> badRequest(String... params) {
        return error(HttpStatus.BAD_REQUEST, "operation.bad_request", params);
    }

    public static <T> ResponseEntity<RestResponse<T>> badRequest(String messageCode, String... params) {
        return error(HttpStatus.BAD_REQUEST, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> internalServerError(String... params) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "operation.internal_server_error", params);
    }

    public static <T> ResponseEntity<RestResponse<T>> internalServerError(String messageCode, String... params) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> unauthorized(String... params) {
        return error(HttpStatus.UNAUTHORIZED, "operation.unauthorized", params);
    }

    public static <T> ResponseEntity<RestResponse<T>> unauthorized(String messageCode, String... params) {
        return error(HttpStatus.UNAUTHORIZED, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> forbidden(String... params) {
        return error(HttpStatus.FORBIDDEN, "operation.forbidden", params);
    }

    public static <T> ResponseEntity<RestResponse<T>> forbidden(String messageCode, String... params) {
        return error(HttpStatus.FORBIDDEN, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> conflict(String... params) {
        return error(HttpStatus.CONFLICT, "operation.conflict", params);
    }

    public static <T> ResponseEntity<RestResponse<T>> conflict(String messageCode, String... params) {
        return error(HttpStatus.CONFLICT, messageCode, params);
    }

    public static <T> ResponseEntity<RestResponse<T>> noContent() {
        return success(HttpStatus.NO_CONTENT, null, "operation.success");
    }

    public static <T> ResponseEntity<RestResponse<T>> noContent(String messageCode, String... params) {
        return success(HttpStatus.NO_CONTENT, null, messageCode, params);
    }

    public static class ResponseCreatorBuilder<T> {
        private HttpStatus status;
        private T data;
        private String messageCode;
        private String[] params;

        public ResponseCreatorBuilder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseCreatorBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseCreatorBuilder<T> messageCode(String messageCode) {
            this.messageCode = messageCode;
            return this;
        }

        public ResponseCreatorBuilder<T> params(String... params) {
            this.params = params;
            return this;
        }

        public ResponseEntity<RestResponse<T>> build() {
            HttpStatus finalStatus = status != null ? status : HttpStatus.OK;
            String finalMessageCode = messageCode != null && !messageCode.isEmpty() ? messageCode : "operation.success";
            String[] finalParams = params != null ? params : new String[0];

            return finalStatus.is2xxSuccessful() 
                ? success(finalStatus, data, finalMessageCode, finalParams)
                : error(finalStatus, finalMessageCode, finalParams);
        }
    }

    public static <T> ResponseCreatorBuilder<T> builder() {
        return new ResponseCreatorBuilder<>();
    }
}