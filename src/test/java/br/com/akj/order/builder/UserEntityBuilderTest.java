package br.com.akj.order.builder;

import br.com.akj.order.builder.user.UserEntityBuilder;
import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.entity.UserEntity;
import br.com.akj.order.fixture.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEntityBuilderTest {

    @Test
    void shouldBuildUserEntityCorrectly() {
        UserToImportDTO userToImport = Fixture.make(UserToImportDTO.class);

        UserEntity entity = UserEntityBuilder.userEntityBuild(userToImport);

        assertNotNull(entity);
        assertEquals(userToImport.id(), entity.getId());
        assertEquals(userToImport.name(), entity.getName());
        assertEquals(userToImport.orders().size(), entity.getOrders().size());
    }
}
