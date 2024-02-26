package com.six.yummy.review.repository;

import com.six.yummy.like.entity.Like;
import com.six.yummy.order.entity.Order;
import com.six.yummy.review.entity.Review;
import com.six.yummy.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUserAndOrder(User user, Order order);
    List<Review> findByRestaurantIdOrderByCreatedAtDesc(Long restaurantId);
    @Query("SELECT r FROM Review r LEFT JOIN r.likeList l WHERE r.restaurantId = :restaurantId GROUP BY r.id ORDER BY COUNT(l) DESC")
    List<Review> findReviewsByRestaurantIdSortedByLikes(@Param("restaurantId") Long restaurantId);
}


