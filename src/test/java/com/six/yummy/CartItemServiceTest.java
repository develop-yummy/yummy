package com.six.yummy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.six.yummy.cartitem.entity.CartItem;
import com.six.yummy.cartitem.repository.CartItemRepository;
import com.six.yummy.cartitem.responsedto.CartItemListResponse;
import com.six.yummy.cartitem.responsedto.CartItemResponse;
import com.six.yummy.cartitem.service.CartItemService;
import com.six.yummy.menu.entity.Menu;
import com.six.yummy.menu.repository.MenuRepository;
import com.six.yummy.restaurant.entity.Restaurant;
import com.six.yummy.user.entity.User;
import com.six.yummy.user.entity.UserRoleEnum;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private CartItemService cartItemService;

    private User user;
    private Restaurant restaurant;
    private Menu menu;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        // 테스트를 위한 기본 객체 설정
        user = new User("TEST", "PASSWORD", "EMAIL", "PHONENUMBER", UserRoleEnum.ADMIN); // 이 부분은 실제 User 객체에 맞게 수정해야 합니다.
        restaurant = Restaurant.createRestaurant("RESTARANTNAME", "ADDRESS", "CONTENT", "CATEGORY", user);
        menu = Menu.createMenu("MENUNAME", 10000, "MENUCONTENTS", "CATEGORY", restaurant); // 이 부분은 실제 Menu 객체에 맞게 수정해야 합니다.
        cartItem = new CartItem(100000, 5, user, menu); // 이 부분은 실제 CartItem 객체에 맞게 수정해야 합니다.
    }

    @Test
    void cartItemAddShouldAddItem() {
        // given
        // 메뉴 존재 가정
        when(menuRepository.findById(anyLong())).thenReturn(Optional.of(menu));
        // 기존 장바구니 항목 없음을 가정
        when(cartItemRepository.findByUserAndMenuAndOrderIdIsNull(user, menu)).thenReturn(null);
        // 저장 로직은 특별한 반환 없이 수행되므로 별도의 when 설정 필요 없음

        // when
        ResponseEntity<CartItemResponse> response = cartItemService.cartItemAdd(1L, 1, user);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니에 해당 상품이 추가되었습니다", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void cartItemReadShouldReturnItems() {
        // given
        List<CartItem> cartItemList = List.of(cartItem);
        when(cartItemRepository.findByUser_id(user.getId())).thenReturn(cartItemList);

        // when
        List<CartItemListResponse> result = cartItemService.cartItemRead(user);

        // then
        assertFalse(result.isEmpty());
        assertEquals(cartItemList.size(), result.size());
        assertEquals(cartItem.getCount(), result.get(0).getCount());
        verify(cartItemRepository, times(1)).findByUser_id(user.getId());
    }

    @Test
    void cartItemDeleteShouldDeleteItem() {
        // given
        Long cartItemId = 1L;
        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        // when
        ResponseEntity<CartItemResponse> response = cartItemService.cartItemDelete(cartItemId, user);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니에 해당 상품이 삭제되었습니다", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).delete(cartItem);
    }

    @Test
    void cartItemDeleteAllShouldDeleteAllItems() {
        // given
        List<CartItem> cartItemList = List.of(cartItem);
        when(cartItemRepository.findByUser_id(user.getId())).thenReturn(cartItemList);

        // when
        ResponseEntity<CartItemResponse> response = cartItemService.cartItemDeleteAll(user);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니를 비웠습니다.", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).deleteAll(cartItemList);
    }

}
