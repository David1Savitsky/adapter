package com.savitsky.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdapterResponse<T> {
    private T response;
    private Status status;
    private String message;

    public enum Status {
        OK,
        ERROR,
    }

    public static <T> AdapterResponse<T> ok(T response) {
        return new AdapterResponse<>(response, Status.OK, "Success");
    }

    public static <T> AdapterResponse<T> error(String message) {
        return new AdapterResponse<>(null, Status.ERROR, message);
    }

    public static <T> AdapterResponse<T> error(T response) {
        return new AdapterResponse<>(response, Status.ERROR, null);
    }
}
