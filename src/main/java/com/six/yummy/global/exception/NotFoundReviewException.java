package com.six.yummy.global.exception;

public class NotFoundReviewException extends IllegalArgumentException{

    public NotFoundReviewException() {
        super("리뷰를 찾을 수 없습니다.");
    }

}
