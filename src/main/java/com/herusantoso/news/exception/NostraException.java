package com.herusantoso.news.exception;

import com.herusantoso.news.enums.StatusCode;

public class NostraException extends RuntimeException {

    private StatusCode code = StatusCode.ERROR;

    public NostraException(){
        super();
    }

    public NostraException(String message){
        super(message);
    }

    public NostraException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }

}
