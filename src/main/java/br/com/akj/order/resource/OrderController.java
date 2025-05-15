package br.com.akj.order.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.akj.order.service.user.OrderImportServic;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderImportServic helloUserService;


}
