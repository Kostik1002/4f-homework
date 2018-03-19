package io.fourfinanceit.model.enums;

import java.util.stream.Stream;

public enum ClientStatus {
    CLEAR("CLEAR"),
    DIRTY("DIRTY");

    private final String value;

    ClientStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClientStatus getByValue(String value) {
        return Stream.of(values())
                .filter(status -> status.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("ClientStatus with value '%s' does not exist", value)));
    }
}
