package br.com.akj.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    @NotNull
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}