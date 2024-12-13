package com.models;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Order {

    private int id;
    private String orderNumber;
    private String items;
    private int tableNumber;
    private String waiterName;
}