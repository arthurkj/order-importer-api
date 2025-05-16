package br.com.akj.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "Product")
@Table(name = "products")
public class ProductEntity {

    @Id
    private Long id;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
