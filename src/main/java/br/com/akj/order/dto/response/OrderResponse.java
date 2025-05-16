package br.com.akj.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(@JsonProperty("order_id") Long orderId, @JsonProperty("total") BigDecimal totalValue, @JsonProperty("date") LocalDate purchaseDate, List<ProductResponse> products) {
}
