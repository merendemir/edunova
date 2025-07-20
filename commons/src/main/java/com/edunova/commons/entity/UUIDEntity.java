package com.edunova.commons.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class UUIDEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;
}