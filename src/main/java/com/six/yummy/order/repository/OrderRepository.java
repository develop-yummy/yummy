package com.six.yummy.order.repository;

import com.six.yummy.order.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    Order findOrderByIdDescTop1();

    Order getTopBy();

    List<Order> findAllByUserId(Long userId);

}
