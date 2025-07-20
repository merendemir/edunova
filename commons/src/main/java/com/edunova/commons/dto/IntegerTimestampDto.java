package com.edunova.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class IntegerTimestampDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
