package com.six.yummy.global.exception;

public class ValidateReviewException extends RuntimeException{

    public ValidateReviewException() {
        super("자신의 리뷰만 삭제할 수 있습니다.");
    }

}
