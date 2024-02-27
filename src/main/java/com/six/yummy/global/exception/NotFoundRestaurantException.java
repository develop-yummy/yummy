package com.six.yummy.global.exception;

public class NotFoundRestaurantException extends IllegalArgumentException{

    public NotFoundRestaurantException() {
        super("식당을 찾을 수 없습니다.");
    }
}
