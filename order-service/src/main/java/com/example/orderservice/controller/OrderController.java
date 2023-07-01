package com.example.orderservice.controller;

import com.example.basedomains.dto.Orders;
import com.example.basedomains.dto.OrdersEvent;
import com.example.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/orders")
    public String placeOrder(@RequestBody Orders order){
        order.setOrderId(UUID.randomUUID().toString());
        OrdersEvent ordersEvent = new OrdersEvent();
        ordersEvent.setStatus("PENDING");
        ordersEvent.setMessage("Order status is in pending state.");
        ordersEvent.setOrder(order);
        orderProducer.sendMessage(ordersEvent);
        return "Order Placed Successfully.";
    }
}
