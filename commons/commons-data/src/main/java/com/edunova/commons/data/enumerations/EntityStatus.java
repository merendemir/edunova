package com.edunova.commons.data.enumerations;

import lombok.Getter;

@Getter
public enum EntityStatus {
    DELETED(-1),
    PASSIVE(0),
    ACTIVE(1);

    private final int status;

    EntityStatus(int status) {
        this.status = status;
    }
}
