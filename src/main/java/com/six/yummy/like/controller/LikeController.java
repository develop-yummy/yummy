package com.six.yummy.like.controller;


import com.six.yummy.like.responsedto.LikeResponse;
import com.six.yummy.like.service.LikeService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurant/reviews")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{reveiwId}/likes")
    public ResponseEntity<LikeResponse> createLike(@PathVariable Long reveiwId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createLike(reveiwId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{likeId}/likes/delete")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteLike(likeId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }


}
