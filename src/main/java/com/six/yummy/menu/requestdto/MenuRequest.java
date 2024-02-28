package com.six.yummy.menu.requestdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {

    @NotBlank
    private String menuName;

    @Min(value = 0, message = "메뉴 가격을 입력해 주세요")
    private long menuPrice;

    @NotBlank
    private String menuContents;

    @NotBlank
    private String category;

}
