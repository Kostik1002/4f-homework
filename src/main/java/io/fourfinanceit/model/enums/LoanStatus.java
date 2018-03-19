package io.fourfinanceit.model.enums;

import java.util.stream.Stream;

public enum LoanStatus {
    OPENED("OPENED"),
    CLOSED("CLOSED");

    private final String value;

    LoanStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LoanStatus getByValue(String value) {
        return Stream.of(values())
                .filter(status -> status.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("LoanStatus with value '%s' does not exist", value)));
    }

}
