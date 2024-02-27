package com.six.yummy.cartitem.repository;

import com.six.yummy.cartitem.entity.CartItem;
import com.six.yummy.menu.entity.Menu;
import com.six.yummy.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser_id(Long userId);

    CartItem findByUserAndMenuAndOrderIdIsNull(User user, Menu menu);

    List<CartItem> findAllByUser_idAndOrderIdIsNull(Long userId);

    List<CartItem> findAllByOrderId(Long orderId);
}
