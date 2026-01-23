package com.springexercise.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
        "code",
        "message",
        "description",
        "data",
        "timestamp"
})
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    private String code;
    private String message;
    private String description;
    private Object data;
    private Long timestamp;


    private Response(String code, String message, String description, Object data) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    private Response(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public static Response success(String message , String description) {
        return new Response("200", message, description);
    }

    public static Response success(String code, String message, String description, Object data) {
        return new Response(code, message, description, data);
    }

    public static Response success(String code, String message , String description) {
        return new Response(code, message, description);
    }

    public static Response error(String code, String message, String description) {
        return new Response(code, message, description);
    }

    public static Response error(String code, String message , String description , Object data) {
        return new Response(code, message, description, data);
    }

    public static Response badRequest(String code, String message, String description) {
        return new Response("400", message, description);
    }
}


