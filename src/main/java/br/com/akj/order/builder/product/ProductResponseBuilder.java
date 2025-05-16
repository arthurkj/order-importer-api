package br.com.akj.order.builder.product;

import br.com.akj.order.dto.response.ProductResponse;
import br.com.akj.order.entity.ProductEntity;

public class ProductResponseBuilder {

    public static ProductResponse productResponseBuilder(final ProductEntity productEntity) {
        return new ProductResponse(productEntity.getProductId(), productEntity.getValue());
    }
}
