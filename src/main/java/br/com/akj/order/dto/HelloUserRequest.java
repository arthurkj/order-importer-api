package br.com.akj.order.dto;

import jakarta.validation.constraints.NotBlank;

public record HelloUserRequest(@NotBlank String name) {

}
