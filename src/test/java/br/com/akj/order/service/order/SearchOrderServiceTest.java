package br.com.akj.order.service.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.akj.order.dto.request.SearchOrderFilterRequest;
import br.com.akj.order.dto.response.OrderResponse;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.fixture.Fixture;
import br.com.akj.order.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SearchOrderServiceTest {

    @InjectMocks
    private SearchOrderService service;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void search_with_filters_returns_orders() {
        final SearchOrderFilterRequest filter = new SearchOrderFilterRequest();
        filter.setOrderId(123L);
        filter.setStartDate(LocalDate.now().minusDays(5));
        filter.setEndDate(LocalDate.now());

        final OrderEntity entity = Fixture.make(OrderEntity.class);

        when(orderRepository.findAll(org.mockito.ArgumentMatchers.any()))
                .thenReturn(List.of(entity));

        final List<OrderResponse> result = service.search(filter);

        assertEquals(1, result.size());
    }
}