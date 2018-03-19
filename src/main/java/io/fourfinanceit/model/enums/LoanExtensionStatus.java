package io.fourfinanceit.model.enums;

import java.util.stream.Stream;

public enum LoanExtensionStatus {
    INITIAL("INITIAL"),
    EXTENDED("EXTENDED");

    private final String value;

    LoanExtensionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LoanExtensionStatus getByValue(String value) {
        return Stream.of(values())
                .filter(status -> status.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("LoanExtensionStatus with value '%s' does not exist", value)));
    }
}
