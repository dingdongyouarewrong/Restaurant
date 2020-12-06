package com.gsu.restaurant;


import com.google.gson.Gson;
import com.gsu.restaurant.model.Orders;
import com.gsu.restaurant.service.OrdersService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gsu.restaurant.Constants.*;

@SpringBootApplication
@RestController
public class ControllerOrders {
    public ControllerOrders(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    OrdersService ordersService;

    @GetMapping("/debug_obj")
    public String debugObj(@RequestParam(value = "name", defaultValue = "World") String name) {
        Orders order = Orders.builder()
                .time_of_adding(SAMPLE_TIME)
                .price(1).ready(false)
                .delivered(false)
                .client_name(SAMPLE_NAME)
                .dish_name(SAMPLE_DISH_NAME)
                .build();
        ordersService.saveOrder(order);
        order = Orders.builder()
                .time_of_adding("00-00-0000, 00:00")
                .price(1).ready(false)
                .delivered(false)
                .client_name("sample name")
                .dish_name("samole dish name")
                .build();
        ordersService.saveOrder(order);
        order = Orders.builder()
                .time_of_adding("00-00-0000, 00:00")
                .price(1).ready(false)
                .delivered(false)
                .client_name("sample name")
                .dish_name("samole dish name")
                .build();
        ordersService.saveOrder(order);

        return "success";
    }


    @GetMapping("/unready_orders")
    public String unreadyOrders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUnreadyOrders();
        return new Gson().toJson(orders);
    }

    @GetMapping("/ready_orders")
    public String readyOrders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUndeliveredOrders();
        return new Gson().toJson(orders);
    }

    @GetMapping("/delivered_orders")
    public String deliveredOrders(@RequestParam(value = "name", defaultValue = "World") String name) {

        List<Orders> orders = ordersService.getDeliveredOrders();
        return new Gson().toJson(orders);
    }

    @PostMapping("/add_order")
    public String addOrder(@RequestBody Orders order) {
        ordersService.saveOrder(order);
        return null;
    }

    @PutMapping("/set_delivered")
    public String setDelivered(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateDelivered(order.getId(), true);
        return String.valueOf(newOrder.getId());
    }

    @PutMapping("/set_ready")
    public String setReady(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateReady(order.getId(), true);
        return String.valueOf(newOrder.getId());
    }
}