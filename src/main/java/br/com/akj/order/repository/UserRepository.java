package br.com.akj.order.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.akj.order.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);
}
