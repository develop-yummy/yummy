package com.six.yummy.global.exception;

public class NotFoundOrderException extends IllegalArgumentException {

    public NotFoundOrderException() {
        super("해당 주문을 찾을 수 없습니다.");
    }
}
