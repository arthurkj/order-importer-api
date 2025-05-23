package br.com.akj.order.resource.handler;

import static br.com.akj.order.errors.Error.INTERNAL_ERROR;
import static br.com.akj.order.errors.Error.INVALID_PARAMETERS;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.akj.order.errors.Error;
import br.com.akj.order.errors.dto.ErrorDTO;
import br.com.akj.order.errors.dto.ErrorMessage;
import br.com.akj.order.exception.AbstractErrorException;
import br.com.akj.order.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ValidationHandler {

    private static final String MESSAGE_ERROR_LOG = "Error on API";

    private final MessageHelper messageHelper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleInternalError(final Exception exception) {
        log.error(MESSAGE_ERROR_LOG, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, INTERNAL_ERROR.getHttpStatus());
    }

    @ExceptionHandler(AbstractErrorException.class)
    public ResponseEntity<ErrorDTO> handleGenericException(final AbstractErrorException exception) {
        log.error(MESSAGE_ERROR_LOG, exception);

        final ErrorDTO errorInfo = new ErrorDTO(exception);

        return new ResponseEntity<>(errorInfo, exception.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleInvalidParameters(final MethodArgumentNotValidException exception) {
        log.error(MESSAGE_ERROR_LOG, exception);
        final Error error = INVALID_PARAMETERS;

        final List<ErrorMessage> errorsParameters = exception.getFieldErrors().stream()
            .map(ErrorMessage::new).toList();

        final ErrorDTO errorInfo = new ErrorDTO(error, messageHelper.get(error, exception.getMessage()),
            errorsParameters);

        return new ResponseEntity<>(errorInfo, error.getHttpStatus());
    }
}
