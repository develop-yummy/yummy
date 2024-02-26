package com.six.yummy.address.controller;

import com.six.yummy.address.requestDto.AddressRequest;
import com.six.yummy.address.requestDto.AddressUpdateRequest;
import com.six.yummy.address.requestDto.DeleteAddressRequest;
import com.six.yummy.address.responseDto.AddressInfoResponse;
import com.six.yummy.address.responseDto.AddressResponse;
import com.six.yummy.address.service.AddressService;
import com.six.yummy.user.lmpl.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/addresses")
public class AddressController {

    private final AddressService addressService;

    // 주소 등록
    @PostMapping
    public void createAddress(@RequestBody AddressRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        addressService.createAddress(request, userDetails.getUser());
    }

    // 주소 전체 조회
    @GetMapping
    public List<AddressResponse> getAddresses() {
        return addressService.getAddresss();
    }

    // 주소 수정
    @PutMapping("/{addressId}")
    public AddressInfoResponse updateTodo(@PathVariable Long addressId, @RequestBody AddressUpdateRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return addressService.updateAddress(addressId, request, userDetails.getUser());
    }

    // 주소 삭제
    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Long addressId, @RequestBody DeleteAddressRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        addressService.deleteAddress(addressId, request, userDetails.getUser());
    }



}
