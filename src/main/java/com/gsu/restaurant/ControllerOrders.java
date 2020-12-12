package com.gsu.restaurant;

import com.gsu.restaurant.model.Orders;
import com.gsu.restaurant.service.OrdersService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gsu.restaurant.Constants.*;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
                .time_of_adding(SAMPLE_TIME)
                .price(1).ready(true)
                .delivered(false)
                .client_name(SAMPLE_NAME)
                .dish_name(SAMPLE_DISH_NAME)
                .build();
        ordersService.saveOrder(order);
        order = Orders.builder()
                .time_of_adding(SAMPLE_TIME)
                .price(1).ready(true)
                .delivered(true)
                .client_name(SAMPLE_NAME)
                .dish_name(SAMPLE_DISH_NAME)
                .build();
        ordersService.saveOrder(order);

        return "success";
    }


    @GetMapping("/unready_orders")
    public List<Orders> unreadyOrders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUnreadyOrders();
        System.out.println("/undelivered orders");
        return orders;
    }

    @GetMapping("/ready_orders")
    public List<Orders> readyOrders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getUndeliveredOrders();
        System.out.println("/ready orders");
        return orders;
    }

    @GetMapping("/delivered_orders")
    public List<Orders> deliveredOrders(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Orders> orders = ordersService.getDeliveredOrders();
        System.out.println("/delivered_orders");
        return orders;
    }

    @PostMapping("/add_order")
    public String addOrder(@RequestBody Orders order) {
        ordersService.saveOrder(order);
        System.out.println("/add order");
        return null;
    }

    @PutMapping("/set_delivered")
    public String setDelivered(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateDelivered(order.getId(), true);
        System.out.println("/set delivered");
        return String.valueOf(newOrder.getId());
    }

    @PutMapping("/set_ready")
    public String setReady(@RequestBody Orders order) {
        Orders newOrder = ordersService.updateReady(order.getId(), true);
        System.out.println("/set ready");
        return String.valueOf(newOrder.getId());
    }
}