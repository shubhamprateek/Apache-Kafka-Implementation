package com.example.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String orderId;
    private String name;
    private int qty;
    private double price;

    private String email;
}
