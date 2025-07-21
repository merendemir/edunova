package com.edunova.commons.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@MappedSuperclass
@FieldNameConstants
public abstract class UUIDEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;
}