package com.six.yummy.global.exception;

public class NotFoundOrderException extends IllegalArgumentException {

    public NotFoundOrderException(){
        super("해당 주문은 삭제되었습니다.");
    }
}
