package com.six.yummy.review.controller;

import com.six.yummy.like.entity.Like;
import com.six.yummy.review.requestdto.ReviewRequest;
import com.six.yummy.review.responsedto.ReviewResponse;
import com.six.yummy.review.service.ReviewService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("orders/{orderId}/reviews")
    public ResponseEntity<ReviewResponse> postReview (@PathVariable Long orderId , @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ReviewRequest reviewRequest){

        ReviewResponse reviewResponse = reviewService.createReview(orderId, userDetails.getUser(), reviewRequest);
        return ResponseEntity.status(201).body(reviewResponse);
    }

    @GetMapping("restaurants/{restaurantId}/review/date")
    public ResponseEntity<List<ReviewResponse>> getReviewSortedByDate(@PathVariable Long restaurantId) {
        return ResponseEntity.status(200).body(reviewService.getReviewsByRestaurantId(restaurantId));

    }

    @GetMapping("restaurants/{restaurantId}/review/like")
    public ResponseEntity<List<ReviewResponse>> getReviewSortedByLike(@PathVariable Long restaurantId){
        return ResponseEntity.status(200).body(reviewService.getReviewsByLike(restaurantId));
    }

    @DeleteMapping("/restaurants/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        reviewService.deleteReview(reviewId, userDetails.getUser());
        return ResponseEntity.status(200).build();

    }





}
