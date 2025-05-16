package br.com.akj.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductResponse(@JsonProperty("product_id") Long productId, BigDecimal value) {
}
