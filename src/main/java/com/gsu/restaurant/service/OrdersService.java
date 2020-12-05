package com.gsu.restaurant.service;

import com.gsu.restaurant.model.Orders;
import com.gsu.restaurant.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository)  {
        this.ordersRepository = ordersRepository;
    }



    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return ordersRepository.getOne(id);
    }

    public List<Orders> getUnreadyOrders() {
        return ordersRepository.findByReady(false);
    }

    public List<Orders> getUndeliveredOrders() {
        return ordersRepository.findByDelivered(false);
    }

    public List<Orders> getDeliveredOrders() {
        return ordersRepository.findByDelivered(true);
    }

    public void saveOrder(Orders order) {
        ordersRepository.save(order);
    }

    @Autowired
    public void setReady(long id) {
        ordersRepository.update(id);
    }
}