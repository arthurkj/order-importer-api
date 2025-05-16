package br.com.akj.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    @NotNull
    private String name;
}