package com.six.yummy.cartitem.service;

import com.six.yummy.cartitem.entity.CartItem;
import com.six.yummy.cartitem.repository.CartItemRepository;
import com.six.yummy.cartitem.responsedto.CartItemListResponse;
import com.six.yummy.cartitem.responsedto.CartItemResponse;
import com.six.yummy.global.exception.NotFoundCartItemException;
import com.six.yummy.global.exception.NotFoundMenuException;
import com.six.yummy.menu.entity.Menu;
import com.six.yummy.menu.repository.MenuRepository;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.jwt.JwtUtil;
import com.six.yummy.user.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final MenuRepository menuRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public ResponseEntity<CartItemResponse> cartItemAdd(
        Long menuId, int count, User user) {

        // 메뉴 검증 로직
        Menu menu = menuRepository.findById(menuId).orElseThrow(
            NotFoundMenuException::new
        );

        // 동일한 메뉴와 동일한 사용자가 있는지 확인
        CartItem existingCartItem = cartItemRepository.findByUserAndMenuAndOrderIdIsNull(user, menu);

        if (existingCartItem != null) {
            existingCartItem.updateCartItemQuantityAndTotalPrice(count);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem saveCartItem = CartItem.builder()
                .totalPrice((int) (menu.getMenuPrice() * count))
                .count(count)
                // menuId와 userId가 추가로 들어가야 함
                .user(user)
                .menu(menu)
                .build();

            cartItemRepository.save(saveCartItem);
        }

        return new ResponseEntity<>(new CartItemResponse("장바구니에 해당 상품이 추가되었습니다"), HttpStatus.OK);

    }

    public List<CartItemListResponse> cartItemRead(User user) {
        // 유저 검증 로직

        // 장바구니 조회 및 리턴넴
        Long userId = user.getId();

//        return new CartItemListResponse(cartItems);
        return cartItemRepository.findByUser_id(userId).stream().map(
            (CartItem cartItem) -> new CartItemListResponse(
                cartItem.getMenu().getMenuId(),
                cartItem.getCount(),
                cartItem.getTotalPrice()
            )).toList();
    }


    public ResponseEntity<CartItemResponse> cartItemDelete(Long cartItemId, User user) {

        // 장바구니 검증 로직
        CartItem deleteCartItem = cartItemRepository.findById(cartItemId).orElseThrow(
            NotFoundCartItemException::new
        );

        if (!Objects.equals(deleteCartItem.getUser().getId(), user.getId())) {
            throw new IllegalArgumentException("직접 생성한 유저 에러로 변경 예정입니다.");
        }

        cartItemRepository.delete(deleteCartItem);

        return new ResponseEntity<>(new CartItemResponse("장바구니에 해당 상품이 삭제되었습니다"), HttpStatus.OK);
    }

    public ResponseEntity<CartItemResponse> cartItemDeleteAll(User user) {

        List<CartItem> cartItems = cartItemRepository.findByUser_id(user.getId());

        if (!cartItems.isEmpty()) {
            cartItemRepository.deleteAll(cartItems);
            return new ResponseEntity<>(new CartItemResponse("장바구니를 비웠습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CartItemResponse("장바구니에 삭제할 내역이 없습니다."),
            HttpStatus.NOT_FOUND);
    }


    public void inputOrderId(Long orderId, User user) {
        List<CartItem> cartItems = cartItemRepository.findAllByUser_idAndOrderIdIsNull(
            user.getId());
        if (cartItems.isEmpty()) {
            throw new NullPointerException("장바구니가 비었습니다");
        }
        for (CartItem cartItem : cartItems) {
            cartItem.inputOrderId(orderId);
        }
    }

    public List<CartItemListResponse> getOrderItems(Long orderId) {

        return cartItemRepository.findAllByOrderId(orderId).stream().map(
            (CartItem cartItem) -> new CartItemListResponse(
                cartItem.getMenu().getMenuId(),
                cartItem.getCount(),
                cartItem.getTotalPrice()
            )).toList();
    }
}
