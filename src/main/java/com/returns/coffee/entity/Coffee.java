package com.returns.coffee.entity;


import com.returns.order.entity.OrderCoffee;
import com.returns.values.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false, length = 30)
    private String korName;

    @Column(nullable = false, length = 30)
    private String engName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false, length = 5))
    private Money price;

    @Column(nullable = false, length = 3, updatable = false)
    private String coffeeCode;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    //i Coffee와 OrderCoffee간의 1:N 매핑
    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    //i Coffee와 OrderCoffee간 안전한 양방향 매핑을 위한 코드
    public void setOrderCoffee(OrderCoffee orderCoffee) {
        this.orderCoffees.add(orderCoffee);
        if (orderCoffee.getCoffee() != this)
            orderCoffee.setCoffee(this);
    }

    public enum CoffeeStatus {

        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("매진");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }

}
