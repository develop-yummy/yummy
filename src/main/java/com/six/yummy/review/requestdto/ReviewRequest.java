package com.six.yummy.review.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ReviewRequest {

    @NotBlank
    @Pattern(regexp = "^[1-5]{1}", message = "1-5 사이의 숫자 하나만 입력해주세요")
    private Short point;
    @NotBlank
    @Pattern(regexp = "^[a-z|A-Z|ㄱ-ㅎ|가-힣| ]*${5,200}", message = "5자 이상 200자 이하 한글과 영어만 가능합니다")
    private String content;


}
