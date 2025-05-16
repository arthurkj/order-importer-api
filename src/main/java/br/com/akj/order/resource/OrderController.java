package br.com.akj.order.resource;

import br.com.akj.order.dto.request.SearchOrderFilterRequest;
import br.com.akj.order.dto.response.OrderResponse;
import br.com.akj.order.dto.response.UserResponse;
import br.com.akj.order.service.order.SearchOrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.akj.order.service.order.ImportOrderFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final ImportOrderFileService importOrderFileService;
    private final SearchOrderService searchOrderService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<UserResponse>> importFile(@ModelAttribute("file") final MultipartFile file) {
        return ResponseEntity.ok(importOrderFileService.importFile(file));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderResponse>> search(final SearchOrderFilterRequest request) {
        return ResponseEntity.ok(searchOrderService.search(request));
    }
}
