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

import static br.com.akj.order.service.order.ImportOrderFileConfig.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportOrderFileService {

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

        final String[] lines = content.split(LINE_BREAK);
        for (String line : lines) {
            Long userId = Long.parseLong(line.substring(USER_ID_START, USER_ID_END));
            String userName = line.substring(USER_NAME_START, USER_NAME_END).trim();
            Long orderId = Long.parseLong(line.substring(ORDER_ID_START, ORDER_ID_END));
            Long productId = Long.parseLong(line.substring(PRODUCT_ID_START, PRODUCT_ID_END));
            BigDecimal productValue = new BigDecimal(line.substring(PRODUCT_VALUE_START, PRODUCT_VALUE_END).trim());
            LocalDate purchaseDate = LocalDate.parse(line.substring(PURCHASE_START, PURCHASE_END), DateTimeFormatter.ofPattern(DATA_FORMATTER_PATTERN));

            UserToImportDTO user = users.computeIfAbsent(userId, id -> new UserToImportDTO(id, userName, new HashMap<>()));

            OrderToImportDTO order = user.orders().computeIfAbsent(orderId, id -> new OrderToImportDTO(id, purchaseDate, new ArrayList<>()));

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
