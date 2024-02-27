package com.six.yummy.global.exception;

public class NotFoundCartItemException extends IllegalArgumentException{

    public NotFoundCartItemException() {
        super("장바구니 내 해당 상품을 찾을 수 없습니다.");
    }
}
