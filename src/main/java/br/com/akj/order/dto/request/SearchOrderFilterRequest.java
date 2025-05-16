package br.com.akj.order.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchOrderFilterRequest {

    private Long orderId;
    private LocalDate startDate;
    private LocalDate endDate;

}
