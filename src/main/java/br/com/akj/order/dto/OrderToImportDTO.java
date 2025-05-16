package br.com.akj.order.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderToImportDTO(Long id, LocalDate purchaseDate, List<ProductToImportDTO> products) {
}
