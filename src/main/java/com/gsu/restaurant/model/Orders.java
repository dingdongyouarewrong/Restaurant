package com.gsu.restaurant.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue
    private Long id;
    private String time_of_adding;
    private float price;
    private String client_name;
    private String dish_name;
    private boolean ready;

    private boolean delivered;

}
