package com.six.yummy.global.exception;

public class DataIntegrityViolationFollowException extends RuntimeException{

    public DataIntegrityViolationFollowException() {
        super("이미 팔로우 된 식당입니다.");
    }
}
