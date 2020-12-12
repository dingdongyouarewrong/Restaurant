package com.gsu.restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrdersDTO {
    private Long id;
    private String time_of_adding;
    private float price;
    private String client_name;
    private String dish_name;
}
