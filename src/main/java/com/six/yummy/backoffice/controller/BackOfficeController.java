package com.six.yummy.backoffice.controller;

import com.six.yummy.backoffice.responsedto.RestaurantSalesResponse;
import com.six.yummy.backoffice.responsedto.TotalSalesResponse;
import com.six.yummy.backoffice.service.BackOfficeService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackOfficeService backOfficeService;

    @GetMapping("/backoffice/restaurants/sales")
    public ResponseEntity<List<RestaurantSalesResponse>> getSales(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<RestaurantSalesResponse> restaurantSalesResponses = backOfficeService.getSales(userDetails.getUser());
        return new ResponseEntity<>(restaurantSalesResponses, HttpStatus.OK);
    }

//    @GetMapping("/backoffice/sales/month")
//    public ResponseEntity<List<TotalSalesResponse>> getTotalSales(
//        @AuthenticationPrincipal UserDetailsImpl userDetails){
//        List<TotalSalesResponse> totalSalesResponses = backOfficeService.getTotalSales(userDetails.getUser());
//        return new ResponseEntity<>(totalSalesResponses, HttpStatus.OK);
//    }
}
