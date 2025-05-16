package br.com.akj.order.builder.product;

import br.com.akj.order.dto.ProductToImportDTO;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.ProductEntity;

public class ProductEntityBuilder {

    public static ProductEntity productEntityBuild(final ProductToImportDTO productToImport, final OrderEntity orderEntity) {
        final ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productToImport.id());
        productEntity.setValue(productToImport.value());

        productEntity.setOrder(orderEntity);

        return productEntity;
    }
}
