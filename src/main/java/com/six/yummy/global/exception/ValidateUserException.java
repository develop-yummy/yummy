package com.six.yummy.global.exception;

public class ValidateUserException extends RuntimeException {
    public ValidateUserException() {
        super("해당 유저에 대한 권한이 없습니다.");
    }

    public ValidateUserException(String s) {
        super(s);
    }

}
