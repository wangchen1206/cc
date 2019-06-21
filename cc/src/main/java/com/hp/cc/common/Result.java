package com.hp.cc.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 统一的API返回接口
 * 
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8422475523486655584L;
    private Integer code;

    private String msg;

    private T data;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    //private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 使用了类上面那个注解，在这种情况下，只返回code，不会有msg和data
     *
     * @param code
     */
    public Result(Integer code, LocalDateTime date) {
        this.code = code;
        this.date = date;

    }

    public Result(Integer code, String msg, LocalDateTime date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public Result(Integer code, T data, LocalDateTime date) {
        this.code = code;
        this.date = date;
        this.data = data;
    }

    public Result(Integer code, String msg, T data, LocalDateTime date) {
        this.code = code;
        this.date = date;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 在json序列化时，该字段不会显示在json里面 使之不在json序列化结果当中
     *
     * @return
     */
    @JSONField
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getDate() {
        return date;
    }

    /**
     * 成功，返回一个code
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> createBySuccess() {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), LocalDateTime.now());
    }

    /**
     * 成功，返回一个文本供前端提示使用
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> createBySuccessMessage(String msg) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), msg, LocalDateTime.now());
    }

    /**
     * 成功，返回对应的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> createBySuccess(T data) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), data, LocalDateTime.now());
    }

    /**
     * 成功，把消息和数据都返回
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> createBySuccess(String msg, T data) {
        return new Result<T>(ResponseCode.SUCCESS.getCode(), msg, data, LocalDateTime.now());
    }

    /**
     * 失败，返回一个code
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> createByError() {
        return new Result<T>(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getDesc(), LocalDateTime.now());
    }

    /**
     * 失败，返回一个文本供前端提示使用
     *
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> Result<T> createByErrorMessage(String errorMessage) {
        return new Result<T>(ResponseCode.BAD_REQUEST.getCode(), errorMessage, LocalDateTime.now());
    }

    /**
     * 失败，返回一个status和提示
     *
     * @param erroeCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> Result<T> createByErrorCodeMessage(int erroeCode, String errorMessage) {
        return new Result<T>(erroeCode, errorMessage, LocalDateTime.now());
    }

    public enum ResponseCode {

        SUCCESS(200, "SUCCESS"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        ERROR_SIGNATURE(10001, "Error Signature"),
        PRECONDITION_FAILED(10002, "Precondition Failed"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
        NOT_LOGIN(50000, "Not Login"),
        UNAUTHORIZED(401,"Unauthorized"),
        FORBIDDEN(403,"Access Denied"),
    	USER_DISABLE(2019,"User Disable");

        private final int code;
        private final String desc;


        ResponseCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

    }
}
