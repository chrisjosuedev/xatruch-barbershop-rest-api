package dev.chrisjosue.xatruchbarbershopapi.common.enums;

import lombok.Getter;

@Getter
public enum Templates {
    RECOVERY_PASSWORD_TEMPLATE("recovery-template"),
    BOOKING_DETAIL_TEMPLATE("booking-template");

    private final String file;

    Templates(String file) {
        this.file = file;
    }
}
