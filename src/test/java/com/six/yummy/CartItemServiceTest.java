package com.six.yummy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
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
        user = new User("TEST", "PASSWORD", "EMAIL", "PHONENUMBER", UserRoleEnum.ADMIN);
        restaurant = Restaurant.createRestaurant("RESTARANTNAME", "ADDRESS", "CONTENT", "CATEGORY", user);
        menu = Menu.createMenu("MENUNAME", 10000, "MENUCONTENTS", "CATEGORY", restaurant);
        cartItem = new CartItem(100000, 5, user, menu);
    }

    @Test
    void cartItemAddShouldAddItem() {
        when(menuRepository.findById(anyLong())).thenReturn(Optional.of(menu));
        when(cartItemRepository.findByUserAndMenuAndOrderIdIsNull(user, menu)).thenReturn(null);

        ResponseEntity<CartItemResponse> response = cartItemService.cartItemAdd(1L, 1, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니에 해당 상품이 추가되었습니다", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void cartItemReadShouldReturnItems() {
        List<CartItem> cartItemList = List.of(cartItem);
        when(cartItemRepository.findByUser_id(user.getId())).thenReturn(cartItemList);

        List<CartItemListResponse> result = cartItemService.cartItemRead(user);

        assertFalse(result.isEmpty());
        assertEquals(cartItemList.size(), result.size());
        assertEquals(cartItem.getCount(), result.get(0).getCount());
        verify(cartItemRepository, times(1)).findByUser_id(user.getId());
    }

    @Test
    void cartItemDeleteShouldDeleteItem() {
        Long cartItemId = 1L;
        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        ResponseEntity<CartItemResponse> response = cartItemService.cartItemDelete(cartItemId, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니에 해당 상품이 삭제되었습니다", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).delete(cartItem);
    }

    @Test
    void cartItemDeleteAllShouldDeleteAllItems() {
        List<CartItem> cartItemList = List.of(cartItem);
        when(cartItemRepository.findByUser_id(user.getId())).thenReturn(cartItemList);

        ResponseEntity<CartItemResponse> response = cartItemService.cartItemDeleteAll(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("장바구니를 비웠습니다.", response.getBody().getMsg());
        verify(cartItemRepository, times(1)).deleteAll(cartItemList);
    }
}
