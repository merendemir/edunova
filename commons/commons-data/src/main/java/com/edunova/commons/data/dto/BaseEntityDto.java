package com.edunova.commons.data.dto;

import com.edunova.commons.data.enumerations.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseEntityDto {
    private EntityStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
