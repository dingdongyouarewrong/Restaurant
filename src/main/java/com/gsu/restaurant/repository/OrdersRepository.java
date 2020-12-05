package com.gsu.restaurant.repository;

import com.gsu.restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByReady(boolean ready);

    List<Orders> findByDelivered(boolean delivered);

    void update(long id);
}
