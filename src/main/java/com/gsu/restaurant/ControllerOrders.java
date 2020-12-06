package com.gsu.restaurant;


import com.google.gson.Gson;
import com.gsu.restaurant.model.Orders;
import com.gsu.restaurant.service.OrdersService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class ControllerOrders {
    public ControllerOrders(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    OrdersService ordersService;

    @GetMapping("/unready_orders")
    public String unready_orders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUnreadyOrders();
        return new Gson().toJson(orders);
    }

    @GetMapping("/ready_orders")
    public String ready_orders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUndeliveredOrders();
        return new Gson().toJson(orders);
    }

    @GetMapping("/delivered_orders")
    public String delivered_orders(@RequestParam(value = "name", defaultValue = "World") String name) {

        List<Orders> orders = ordersService.getDeliveredOrders();
        return new Gson().toJson(orders);
    }

    @PostMapping("/add_order")
    public String add_order(@RequestBody Orders order) {
        ordersService.saveOrder(order);
        return null;
    }

    @PutMapping("/set_delivered")
    public String set_delivered(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateDelivered(order.getId(), true);
        return String.valueOf(newOrder.getId());
    }

    @PutMapping("/set_ready")
    public String set_ready(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateReady(order.getId(), true);
        return String.valueOf(newOrder.getId());
    }
}