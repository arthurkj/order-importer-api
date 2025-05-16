package br.com.akj.order.errors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum ErrorCode {

    CODE_0001("ORDER_IMPORTER-0001"),
    CODE_0002("ORDER_IMPORTER-0002"),
    CODE_0003("ORDER_IMPORTER-0003");

    private final String code;
}
