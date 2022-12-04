package com.returns.order.entity;

import com.returns.audit.Auditable;
import com.returns.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Entity
public class OrderCoffee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    //i OrderCoffee와 Order의 N:1 연관 관계 매핑
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    //i OrderCoffee와 Coffee의 N;1 연관 관계 매핑
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    //i OrderCoffee와 Order간 안전한 양방향 매핑을 위한 코드
    //i @param order OrderCoffee와 연관관계를 맺을 대상인 order 객체
    public void setOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderCoffees().contains(this))
            this.order.getOrderCoffees().add(this);
    }

    //i OrderCoffee와 Coffee간 안전한 양방향 매핑을 위한 코드
    //i @param coffee    OrderCoffee와 연관관계를 맺을 대상인 coffee 객체
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        if (!this.coffee.getOrderCoffees().contains(this))
            this.coffee.setOrderCoffee(this);
    }
}
