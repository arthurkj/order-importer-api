package br.com.akj.order.repository;

import br.com.akj.order.entity.ProductEntity;
import br.com.akj.order.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {


}
