package com.returns.order.mapper;

import com.returns.coffee.entity.Coffee;
import com.returns.member.entity.Member;
import com.returns.order.dto.OrderCoffeeResponseDto;
import com.returns.order.dto.OrderPatchDto;
import com.returns.order.dto.OrderPostDto;
import com.returns.order.dto.OrderResponseDto;
import com.returns.order.entity.Order;

import com.returns.order.entity.OrderCoffee;
import com.returns.values.Money;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);

    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.setOrder(order);
                    orderCoffee.setCoffee(coffee);
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setMember(member);
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    // homework solution 추가
    default OrderResponseDto orderToOrderResponseDto(Order order){
        // 객체 그래프 탐색을 통해 주문한 커피 정보를 가져온다.
        // N + 1 문제가 발생할 수 있다.
        List<OrderCoffee> orderCoffees = order.getOrderCoffees();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMemberId(order.getMember().getMemberId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreatedAt());

        // 주문한 커피 정보를 List<OrderCoffeeResponseDto>로 변경한다.
        orderResponseDto.setOrderCoffees(orderCoffeesToOrderCoffeeResponseDtos(orderCoffees));

        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderCoffeesToOrderCoffeeResponseDtos(
            List<OrderCoffee> orderCoffees) {
        return orderCoffees
                .stream()
                .map(orderCoffee -> {
                    // 주문 등록 시에는 price 값이 null이므로, null 여부를 체크해야 한다.
                    Money coffeePrice = orderCoffee.getCoffee().getPrice();
                    Integer price = coffeePrice != null ? coffeePrice.getValue() : null;

                    return OrderCoffeeResponseDto
                            .builder()
                            .coffeeId(orderCoffee.getCoffee().getCoffeeId())
                            .quantity(orderCoffee.getQuantity())
//                        .price(orderCoffee.getCoffee().getPrice())  // 레거시 코드
                            .price(price)
                            .korName(orderCoffee.getCoffee().getKorName())
                            .engName(orderCoffee.getCoffee().getEngName())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
