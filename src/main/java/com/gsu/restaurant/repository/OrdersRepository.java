package com.gsu.restaurant.repository;

import com.gsu.restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByReady(boolean ready);

    List<Orders> findByDelivered(boolean delivered);

}
