package com.edunova.commons.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UUIDEntityDto extends BaseEntityDto {
    private UUID id;
}
