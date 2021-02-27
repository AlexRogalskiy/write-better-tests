package com.arhohuttunen.testdatabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.arhohuttunen.testdatabuilder.CustomerBuilder.aCustomer;

public class OrderBuilder {
    private Long orderId = 1L;
    private Customer customer = aCustomer().build();
    private List<OrderItem> orderItems = new ArrayList<>();
    private Double discountRate = 0.0;
    private String couponCode;

    private OrderBuilder() {

    }

    private OrderBuilder(OrderBuilder copy) {
        this.orderId = copy.orderId;
        this.customer = copy.customer;
        this.orderItems = copy.orderItems;
        this.discountRate = copy.discountRate;
        this.couponCode = copy.couponCode;
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withId(Long orderId) {
        OrderBuilder builder = new OrderBuilder(this);
        builder.orderId = orderId;
        return builder;
    }

    public OrderBuilder but() {
        return new OrderBuilder(this);
    }

    public OrderBuilder with(CustomerBuilder customerBuilder) {
        this.customer = customerBuilder.build();
        return this;
    }

    public OrderBuilder with(OrderItemBuilder orderItemBuilder) {
        this.orderItems.add(orderItemBuilder.build());
        return this;
    }

    public OrderBuilder withDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public OrderBuilder withCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public Order build() {
        Order order = new Order(orderId, customer, discountRate, couponCode);
        orderItems.forEach(order::addOrderItem);
        return order;
    }
}
