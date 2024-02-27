package com.six.yummy.global.exception;

public class NotMatchRestaurantException extends RuntimeException{

    public NotMatchRestaurantException() {
        super("장바구니에 다른 식당의 음식을 담을 수 없습니다.");
    }
}
