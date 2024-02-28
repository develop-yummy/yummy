package com.six.yummy.backoffice.responsedto;

import lombok.Getter;

@Getter
public class TotalSalesResponse {

    private Long totalPrice;

    public TotalSalesResponse(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
