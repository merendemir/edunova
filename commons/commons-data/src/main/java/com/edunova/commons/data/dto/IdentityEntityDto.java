package com.edunova.commons.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Setter
@Getter
public class IdentityEntityDto extends BaseEntityDto {
    private Long id;
}
