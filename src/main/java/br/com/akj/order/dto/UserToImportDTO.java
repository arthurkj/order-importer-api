package br.com.akj.order.dto;

import java.util.Map;

public record UserToImportDTO(Long id, String name, Map<Long, OrderToImportDTO> orders) {
}
