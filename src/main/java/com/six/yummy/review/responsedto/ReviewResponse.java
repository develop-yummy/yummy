package com.six.yummy.review.responsedto;

import com.six.yummy.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponse {

    private Long reviewId;
    private String username;
    private Short point;
    private String content;
    private LocalDateTime createdAt;
    private int likeCount;

    public ReviewResponse(Review review) {
        this.reviewId = review.getReviewId();
        this.username = review.getUsername();
        this.point = review.getPoint();
        this.content = review.getContent();
        this.createdAt = LocalDateTime.now();
        this.likeCount = review.getLikesCount();
    }


}
