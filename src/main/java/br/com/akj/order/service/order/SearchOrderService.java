package br.com.akj.order.service.order;

import br.com.akj.order.builder.order.OrderResponseBuilder;
import br.com.akj.order.dto.request.SearchOrderFilterRequest;
import br.com.akj.order.dto.response.OrderResponse;
import br.com.akj.order.entity.OrderEntity;
import br.com.akj.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.akj.order.repository.OrderSpecification.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchOrderService {

    private final OrderRepository orderRepository;

    public List<OrderResponse> search(final SearchOrderFilterRequest filterRequest) {
        Specification<OrderEntity> filters = Specification
                .where(hasOrderId(filterRequest.getOrderId()))
                .and(hasOrderStartDate(filterRequest.getStartDate()))
                .and(hasOrderEndDate(filterRequest.getEndDate()));

        return orderRepository.findAll(filters).stream().map(OrderResponseBuilder::orderResponseBuild).toList();
    }
}
