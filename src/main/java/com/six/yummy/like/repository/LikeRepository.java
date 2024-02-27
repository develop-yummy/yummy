package com.six.yummy.like.repository;

import com.six.yummy.like.entity.Like;
import com.six.yummy.review.entity.Review;
import com.six.yummy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndReview(User user, Review review);

}
