package br.com.akj.order.builder;

import br.com.akj.order.builder.order.OrderEntityBuilder;
import br.com.akj.order.dto.OrderToImportDTO;
import br.com.akj.order.dto.ProductToImportDTO;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.UserEntity;
import br.com.akj.order.fixture.Fixture;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.*;

class OrderEntityBuilderTest {

    @Test
    void shouldBuildOrderEntityCorrectly() {
        Long orderId = nextLong();
        LocalDate purchaseDate = LocalDate.now();
        ProductToImportDTO product1 = Fixture.make(ProductToImportDTO.class);
        ProductToImportDTO product2 = Fixture.make(ProductToImportDTO.class);
        UserEntity user = Fixture.make(UserEntity.class);

        OrderToImportDTO orderDto = new OrderToImportDTO(orderId, purchaseDate, List.of(product1, product2));

        OrderEntity order = OrderEntityBuilder.orderEntityBuild(orderDto, user);

        assertNotNull(order);
        assertEquals(orderId, order.getId());
        assertEquals(purchaseDate, order.getPurchaseDate());
        assertEquals(user, order.getUser());
        assertEquals(2, order.getProducts().size());
    }
}
