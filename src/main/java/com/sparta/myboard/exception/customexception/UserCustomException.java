package com.sparta.myboard.exception.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCustomException extends RuntimeException{
    private String errorMessage;
    private int statusCode;

    public UserCustomException(ErrorCode errorCode) {
        this.errorMessage=errorCode.getErrorMessage();
        this.statusCode=errorCode.getStatusCode();
    }


}
