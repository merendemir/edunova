package com.edunova.commons.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@MappedSuperclass
@FieldNameConstants
public abstract class IdentityEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
}