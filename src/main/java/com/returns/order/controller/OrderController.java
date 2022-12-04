package com.returns.order.controller;

import com.returns.coffee.service.CoffeeService;
import com.returns.member.entity.Member;
import com.returns.member.service.MemberService;
import com.returns.order.dto.OrderPatchDto;
import com.returns.order.dto.OrderPostDto;
import com.returns.order.entity.Order;
import com.returns.order.mapper.OrderMapper;
import com.returns.order.service.OrderService;
import com.returns.response.MultiResponseDto;
import com.returns.response.SingleResponseDto;
import com.returns.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v11/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final MemberService memberService;
    private final static String ORDER_DEFAULT_URL = "/v11/orders";

    public OrderController(OrderService orderService,
                           OrderMapper mapper, MemberService memberService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        // homework solution 추가
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order)),
                HttpStatus.CREATED);
//        URI location =
//                UriComponentsBuilder
//                        .newInstance()
//                        .path(ORDER_DEFAULT_URL + "/{order-id}")
//                        .buildAndExpand(order.getOrderId())
//                        .toUri();               // "/v10/orders/{order-id}"
//
//        return ResponseEntity.created(location).build(); // (3) HTTP 201 Created status
    }

    private void updateStamp(Order order) {
        Member member = memberService.findMember(order.getMember().getMemberId());
        int stampCount =
                order.getOrderCoffees().stream()
                        .map(orderCoffee -> orderCoffee.getQuantity())
                        .mapToInt(quantity -> quantity)
                        .sum();
        Stamp stamp = member.getStamp();
        stamp.setStampCount(stamp.getStampCount() + stampCount);
        member.setStamp(stamp);

        memberService.updateMemberV1(member);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id") @Positive long orderId,
                                     @Valid @RequestBody OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order =
                orderService.updateOrder(mapper.orderPatchDtoToOrder(orderPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order))
                , HttpStatus.OK);
    }
    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);

        // homework solution 추가
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Order> pageOrders = orderService.findOrders(page - 1, size);
        List<Order> orders = pageOrders.getContent();

        // homework solution 추가
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.ordersToOrderResponseDtos(orders), pageOrders),
                HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
