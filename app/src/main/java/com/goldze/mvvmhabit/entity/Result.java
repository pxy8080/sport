package com.goldze.mvvmhabit.entity;
public class Result<T> {
    private Integer code;
    private String message;
    private T result;

    public Result() {
    }

    public Result(Integer code, String msg, T result) {
        this.code = code;
        this.message = msg;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return message;
    }

    public Result setMsg(String msg) {
        this.message = msg;
        return this;
    }

    public T getresult() {
        return result;
    }

    public Result setresult(T result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

}
