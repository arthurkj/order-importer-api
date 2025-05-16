package br.com.akj.order.builder.order;

import br.com.akj.order.builder.product.ProductResponseBuilder;
import br.com.akj.order.dto.response.OrderResponse;
import br.com.akj.order.dto.response.ProductResponse;
import br.com.akj.order.dto.response.UserResponse;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.UserEntity;

import java.util.List;

public class OrderResponseBuilder {
    public static OrderResponse orderResponseBuild(final OrderEntity orderEntity) {
        final List<ProductResponse> products = orderEntity.getProducts().stream().map(ProductResponseBuilder::productResponseBuilder).toList();

        return new OrderResponse(orderEntity.getId(), orderEntity.getTotalValue(), orderEntity.getPurchaseDate(), products);
    }
}
