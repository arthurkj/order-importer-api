package br.com.akj.order.builder.user;

import br.com.akj.order.builder.order.OrderResponseBuilder;
import br.com.akj.order.dto.response.OrderResponse;
import br.com.akj.order.dto.response.UserResponse;
import br.com.akj.order.entity.UserEntity;

import java.util.List;

public class UserResponseBuilder {
    public static UserResponse userResponseBuild(final UserEntity userEntity) {
        final List<OrderResponse> orders = userEntity.getOrders().stream().map(OrderResponseBuilder::orderResponseBuild).toList();

        return new UserResponse(userEntity.getId(), userEntity.getName(), orders);
    }
}
