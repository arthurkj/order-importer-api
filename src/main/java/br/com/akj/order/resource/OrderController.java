package br.com.akj.order.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.akj.order.service.order.OrderImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderImportService orderImportService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@ModelAttribute("file") MultipartFile file) {
        orderImportService.importFile(file);
    }
}
