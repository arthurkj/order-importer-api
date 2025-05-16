package br.com.akj.order.service.order;

import br.com.akj.order.errors.Error;
import br.com.akj.order.exception.BusinessErrorException;
import br.com.akj.order.helper.MessageHelper;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderImportService {

    private final MessageHelper messageHelper;

    public void importFile(@NonNull final MultipartFile file) {
        log.info("Importing order file {}", file.getOriginalFilename());

        String content = readFile(file);
        // converter para entity user
        // salvar user
        // salvar order


        log.info("file {} imported with {} orders", file.getOriginalFilename(), 1);
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
