package br.com.akj.order.exception;

import br.com.akj.order.errors.Error;

public class BusinessErrorException extends AbstractErrorException {

    public BusinessErrorException(final Error error, final String reason) {
        super(error.getHttpStatus(), reason, error.getCode());
    }
}
