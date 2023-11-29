package dev.chrisjosue.xatruchbarbershopapi.common.enums;

import lombok.Getter;

@Getter
public enum Responses {
    DATA ("data"),
    ERRORS ("errors");

    private final String type;

    Responses (String type) {
        this.type = type;
    }
}
