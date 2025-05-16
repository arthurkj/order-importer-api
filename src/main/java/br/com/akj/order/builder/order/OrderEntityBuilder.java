package br.com.akj.order.builder.order;

import br.com.akj.order.dto.OrderToImportDTO;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.UserEntity;

import static br.com.akj.order.builder.product.ProductEntityBuilder.productEntityBuild;

public class OrderEntityBuilder {

    public static OrderEntity orderEntityBuild(final OrderToImportDTO orderToImport, final UserEntity userEntity) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderToImport.id());
        orderEntity.setPurchaseDate(orderToImport.purchaseDate());
        orderEntity.setProducts(orderToImport.products().stream().map(productToImport ->
                productEntityBuild(productToImport, orderEntity)).toList());
        orderEntity.setUser(userEntity);

        return orderEntity;
    }
}
