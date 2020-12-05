package com.gsu.restaurant;


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
        System.out.println("awd");
        return String.format("Jsonstring");
    }

    @GetMapping("/ready_orders")
    public String ready_orders(@RequestParam(value = "name", defaultValue = "World") String name) {

        String Jsonstring = "[ { \"id\": 1, \"dish\": \"somedishes_ready\" }, { \"id\": 2, \"dish\": \"somedishes_ready\" }, { \"id\": 3, \"dish\": \"somedishes_ready\" }, { \"id\": 4, \"dish\": \"somedishes_ready\" }]";

        return String.format(Jsonstring);
    }

    @GetMapping("/delivered_orders")
    public String delivered_orders(@RequestParam(value = "name", defaultValue = "World") String name) {

        String Jsonstring = "[ { \"id\": 1, \"dish\": \"somedishes_ready\" }, { \"id\": 2, \"dish\": \"somedishes_ready\" }, { \"id\": 3, \"dish\": \"somedishes_ready\" }, { \"id\": 4, \"dish\": \"somedishes_ready\" }]";

        return String.format(Jsonstring);
    }

    @PostMapping("/add_order")
    public String add_order(@RequestBody Orders order) {
        ordersService.saveOrder(order);
        return null;
    }

    @PutMapping("/set_delivered")
    public String set_delivered(@RequestBody Orders order) {

        return "{'order':'delivered'}";
    }

    @PutMapping("/set_ready")
    public String set_ready(@RequestBody Orders order) {

        List<Orders> orders = ordersService.getUnreadyOrders();

        ordersService.setReady(order.getId());

        List<Orders> orders1 = ordersService.getUnreadyOrders();

        return "{'order':'delivered'}";
    }
}