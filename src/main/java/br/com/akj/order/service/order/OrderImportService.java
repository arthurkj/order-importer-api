package br.com.akj.order.service.order;

import br.com.akj.order.dto.OrderToImportDTO;
import br.com.akj.order.dto.ProductToImportDTO;
import br.com.akj.order.dto.UserToImportDTO;
import br.com.akj.order.errors.Error;
import br.com.akj.order.exception.BusinessErrorException;
import br.com.akj.order.helper.MessageHelper;
import br.com.akj.order.service.user.SaveUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderImportService {

    private final MessageHelper messageHelper;
    private final SaveUserService saveUserService;

    public void importFile(@NonNull final MultipartFile file) {
        log.info("Importing order file {}", file.getOriginalFilename());

        String content = readFile(file);

        Map<Long, UserToImportDTO> usersToImport = convertContentToUserMap(content);
        saveUserService.saveUser(usersToImport);

        log.info("file {} imported", file.getOriginalFilename());
    }

    private Map<Long, UserToImportDTO> convertContentToUserMap(String content) {
        final Map<Long, UserToImportDTO> users = new HashMap<>();

        final String[] lines = content.split("\n");
        for (String line : lines) {
            Long userId = Long.parseLong(line.substring(0, 10));
            String userName = line.substring(10, 55).trim();
            Long orderId = Long.parseLong(line.substring(55, 65));
            Long productId = Long.parseLong(line.substring(65, 75));
            BigDecimal productValue = new BigDecimal(line.substring(75, 87).trim());
            LocalDate purchaseDate = LocalDate.parse(line.substring(87, 95), DateTimeFormatter.ofPattern("yyyyMMdd"));

            UserToImportDTO user;

            if (users.containsKey(userId)) {
                user = users.get(userId);
            } else {
                user = new UserToImportDTO(userId, userName, new HashMap<>());
                users.put(userId, user);
            }

            OrderToImportDTO order;

            if (user.orders().containsKey(orderId)) {
                order = user.orders().get(orderId);
            } else {
                order = new OrderToImportDTO(orderId, purchaseDate, new ArrayList<>());
                user.orders().put(orderId, order);
            }

            ProductToImportDTO product = new ProductToImportDTO(productId, productValue);
            order.products().add(product);
        }

        return users;
    }

    private String readFile(MultipartFile file) {
        try {
            return new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (final IOException exception) {
            log.error("Error reading file {}: {}", file.getOriginalFilename(), exception.getMessage());
            throw new BusinessErrorException(Error.INVALID_FILE_ERROR, messageHelper.get(Error.INVALID_FILE_ERROR));
        }
    }
}
