package com.nani.boot1.exception;

import com.nani.boot1.common.ErrorCode;

public class InternalServerErrorException extends RuntimeException {
    private int code;
    private String msg;
    public InternalServerErrorException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public InternalServerErrorException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
