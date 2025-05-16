package br.com.akj.order.builder.user;

import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.UserEntity;

import java.util.List;

import static br.com.akj.order.builder.order.OrderEntityBuilder.orderEntityBuild;

public class UserEntityBuilder {

    public static UserEntity userEntityBuild(final UserToImportDTO userToImport) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(userToImport.id());
        userEntity.setName(userToImport.name());

        final List<OrderEntity> orderList = userToImport.orders().values()
                .stream().map(orderToImport ->
                        orderEntityBuild(orderToImport, userEntity)).toList();

        userEntity.setOrders(orderList);
        return userEntity;
    }
}
