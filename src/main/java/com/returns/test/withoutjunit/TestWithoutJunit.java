package com.returns.test.withoutjunit;

import com.returns.order.entity.Order;
import com.returns.order.entity.OrderCoffee;

import java.util.List;

public class TestWithoutJunit {
    public static void main(String[] args) {
        calculateStampCountTest();
        calculateEarnedStampCountTest();
    }

    private static void calculateStampCountTest() {
        //i given
        int nowCount = 5;
        int earned = 3;

        //i when
        int actual = StampCalculator.calculateStampCount(nowCount,earned);
        int excepted = 7;

        //i then
        System.out.println(actual==8);
    }

    private static void calculateEarnedStampCountTest() {
        //i given
        Order order = new Order();
        OrderCoffee orderCoffee1 = new OrderCoffee();
        orderCoffee1.setQuantity(3);

        OrderCoffee orderCoffee2 = new OrderCoffee()    ;
        orderCoffee1.setQuantity(5);

        order.setOrderCoffees(List.of(orderCoffee1, orderCoffee2));
        int expected = orderCoffee1.getQuantity() + orderCoffee2.getQuantity();

        //i when
        int actual = StampCalculator.calculateEarnedStampCount(order);

        //i then
        System.out.println(expected==actual);
    }
}
