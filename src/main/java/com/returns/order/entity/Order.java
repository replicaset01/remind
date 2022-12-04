package com.returns.order.entity;

import com.returns.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    //i Order와 Member간 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") //i Order 테이블의 외래키 컬럼 이름 지정
    private Member member;

    //i Order와 Member간 안전한 양방향 관계를 위한 매핑
    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getOrders().contains(this))
            this.member.getOrders().add(this);
    }

    //i Order와 OrderCoffee간 1:N 관계를 위한 매핑
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderCoffee> orderCoffees = new ArrayList<>();



    public enum OrderStatus {

        ORDER_REQUEST(1, "주문요청"),
        ORDER_CONFIRM(2, "주문확정"),
        ORDER_COMPLETE(3, "주문완료"),
        ORDER_CANCEL(4, "주문취소");

        @Getter
        private int stepNumber;
        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
