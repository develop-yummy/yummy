package com.six.yummy.address.service;

import com.six.yummy.address.entity.Address;
import com.six.yummy.address.repository.AddressRepository;
import com.six.yummy.address.requestDto.AddressRequest;
import com.six.yummy.address.requestDto.AddressUpdateRequest;
import com.six.yummy.address.requestDto.DeleteAddressRequest;
import com.six.yummy.address.responseDto.AddressInfoResponse;
import com.six.yummy.address.responseDto.AddressResponse;
import com.six.yummy.global.util.PasswordEncoderUtil;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.repository.UserRepository;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final UserRepository userRepository;

    // 주소 등록
    @Transactional
    public void createAddress(AddressRequest request, User user) {
        User findUser = findUser(user);
        Address address = new Address(request.getNickname(), request.getCity(), request.getStreet(),
            request.getZipcode(), findUser);
        addressRepository.save(address);

    }

    // 주소 전체 조회
    public List<AddressResponse> getAddresss() {
        List<AddressResponse> responses = addressRepository.findAll().stream().map(
            address -> new AddressResponse(address.getUser().getUsername(), address.getNickname(),
                address.getCity(), address.getStreet(), address.getZipcode())).toList();
        return responses;
    }

    // 주소 수정
    @Transactional
    public AddressInfoResponse updateAddress(Long addressId, AddressUpdateRequest request,
        User user) {
        Address address = findAddressById(addressId);
        if (!user.getId().equals(address.getUser().getId())) {
            throw new RejectedExecutionException("본인만 수정할 수 있습니다.");
        }
        // 비밀번호 일치여부 추가
        if (!passwordEncoderUtil.passwordEncoder()
            .matches(request.getPassword(), address.getUser().getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
        address.updateAddress(request.getNickname(), request.getCity(), request.getStreet(),
            request.getZipcode());
        return new AddressInfoResponse(user.getUsername(), address.getNickname(), address.getCity(),
            address.getStreet(), address.getZipcode());

    }

    // 주소 삭제
    public void deleteAddress(Long addressId, DeleteAddressRequest request, User user) {
        User findUser = findUser(user);
        // 비밀번호 일치여부 추가
        if (!passwordEncoderUtil.passwordEncoder()
            .matches(request.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
        addressRepository.deleteById(addressId);


    }

    private User findUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "없는 사용자입니다."));// .getUser() - 객체라서 null인지 아닌지 상관없이 그 값을 가져오는 거
    }

    private Address findAddressById(Long addressId) {
        return addressRepository.findById(addressId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할일 ID 입니다."));

    }

}
