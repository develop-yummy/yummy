package com.six.yummy.global.exception;

public class DataIntegrityViolationLikeException extends RuntimeException{
    public DataIntegrityViolationLikeException(){
        super("이미 좋아요 하셨습니다.");
    }

}
