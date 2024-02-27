package com.six.yummy.global.exception;

public class DataIntegrityViolationReviewException extends RuntimeException{
    public DataIntegrityViolationReviewException(){
        super("이미 리뷰를 작성하셨습니다.");
    }

}
