package br.com.akj.order.builder;

import br.com.akj.order.builder.product.ProductEntityBuilder;
import br.com.akj.order.dto.ProductToImportDTO;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.ProductEntity;
import br.com.akj.order.fixture.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductEntityBuilderTest {

    @Test
    void shouldBuildProductEntityCorrectly() {
        ProductToImportDTO dto = Fixture.make(ProductToImportDTO.class);
        OrderEntity order = Fixture.make(OrderEntity.class);

        ProductEntity entity = ProductEntityBuilder.productEntityBuild(dto, order);

        assertNotNull(entity);
        assertEquals(dto.id(), entity.getProductId());
        assertEquals(dto.value(), entity.getValue());
        assertEquals(order, entity.getOrder());
    }
}
