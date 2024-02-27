package com.six.yummy.global.exception;

public class NotFoundFollowException extends RuntimeException{

    public NotFoundFollowException() {
        super("팔로우한 식당을 찾을 수 없습니다.");
    }
}
