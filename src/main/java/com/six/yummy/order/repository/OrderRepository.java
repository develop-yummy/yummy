package com.six.yummy.order.repository;

import com.six.yummy.order.entity.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);

    List<Order> findByOrderedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
