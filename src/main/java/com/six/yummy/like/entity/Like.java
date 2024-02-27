package com.six.yummy.like.entity;

import com.six.yummy.like.requestdto.LikeRequest;
import com.six.yummy.review.entity.Review;
import com.six.yummy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "likeReview")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public Like(User user, Review review) {
        this.user = user;
        this.review = review;
    }

    public void setReview(Review review) {
        this.review = review;
        review.getLikeList().add(this);
    }

    public void removeReview(Review review) {
        this.review = review;
        review.getLikeList().remove(this);
    }
}
