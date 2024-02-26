package com.six.yummy.order.repository;

import com.six.yummy.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
