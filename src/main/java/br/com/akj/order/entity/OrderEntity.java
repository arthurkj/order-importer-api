package br.com.akj.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "Order")
@Table(name = "orders")
public class OrderEntity {

    @Id
    private Long id;

    private BigDecimal totalValue;

    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products;
}
