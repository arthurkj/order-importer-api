package br.com.akj.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    public void setProducts(final List<ProductEntity> products) {
        this.products = products;
        this.totalValue = products.stream().map(ProductEntity::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
