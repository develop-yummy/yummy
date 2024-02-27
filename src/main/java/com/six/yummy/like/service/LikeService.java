package com.six.yummy.like.service;

import com.six.yummy.like.entity.Like;
import com.six.yummy.like.repository.LikeRepository;
import com.six.yummy.like.requestdto.LikeRequest;
import com.six.yummy.like.responsedto.LikeResponse;
import com.six.yummy.review.entity.Review;
import com.six.yummy.review.repository.ReviewRepository;
import com.six.yummy.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final ReviewRepository reviewRepository;

    @Transactional
    public LikeResponse createLike(Long reveiwId, User user) {
        Review review = reviewRepository.findById(reveiwId).orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        if (!likeRepository.findByUserAndReview(user, review).isPresent()) {
            Like like = new Like(user, review);
            like.setReview(review); // 해당 리뷰에 좋아요 추가(리스트 형태)
            reviewRepository.save(review);
            likeRepository.save(like);

            return new LikeResponse(like, "좋아요 하셨습니다");
        } else {
            throw new IllegalArgumentException("이미 좋아요 하신 리뷰입니다");
        }
    }

    @Transactional
    public void deleteLike(Long likeId, User user) {
        Like like = likeRepository.findById(likeId).orElseThrow(() -> new IllegalArgumentException("좋아요가 없습니다"));
        Review review = like.getReview();
        if (!user.getId().equals(like.getUser().getId())) {
            throw new RejectedExecutionException("좋아요 하신 적이 없는 리뷰입니다");
        }
        like.removeReview(review);//리뷰의 좋아요 리스트에서 제거.
        reviewRepository.save(review);
        likeRepository.delete(like);
    }
}
