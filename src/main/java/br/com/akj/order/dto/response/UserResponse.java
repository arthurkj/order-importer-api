package br.com.akj.order.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserResponse(@JsonProperty("user_id") Long userId, String name, List<OrderResponse> orders) {
}
