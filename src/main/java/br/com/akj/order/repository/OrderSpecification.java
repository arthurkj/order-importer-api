package br.com.akj.order.repository;

import br.com.akj.order.entity.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class OrderSpecification {

    public static Specification<OrderEntity> hasOrderId(Long orderId) {
        return (root, query, cb) -> {
            if (orderId == null) {
                return null;
            }
            return cb.equal(root.get("id"), orderId);
        };
    }

    public static Specification<OrderEntity> hasOrderStartDate(LocalDate startDate) {
        return (root, query, cb) -> {
            if (startDate == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("purchaseDate"), startDate);
        };
    }

    public static Specification<OrderEntity> hasOrderEndDate(LocalDate endDate) {
        return (root, query, cb) -> {
            if (endDate == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("purchaseDate"), endDate);
        };
    }
}
