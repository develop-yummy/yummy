package com.six.yummy.like.responsedto;

import com.six.yummy.like.entity.Like;
import com.six.yummy.user.entity.User;
import lombok.Getter;

@Getter
public class LikeResponse {
    private Long id;
    private User user;
    private String msg;

    public LikeResponse (Like like, String msg){
        this.id = like.getId();
        this.user = like.getUser();
        this.msg = msg;

    }

}
