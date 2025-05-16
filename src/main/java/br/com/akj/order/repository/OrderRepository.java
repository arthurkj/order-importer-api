package br.com.akj.order.repository;

import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {


}
