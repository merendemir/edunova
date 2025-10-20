package com.edunova.user.service.data.response;

import com.edunova.commons.data.dto.IdentityEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationUserResponse extends IdentityEntityDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}