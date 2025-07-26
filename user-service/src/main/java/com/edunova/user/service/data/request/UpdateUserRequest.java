package com.edunova.user.service.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    
    @NotBlank(message = "{user.firstName.required}")
    @Size(min = 2, max = 50, message = "{user.firstName.size}")
    private String firstName;

    @NotBlank(message = "{user.lastName.required}")
    @Size(min = 2, max = 50, message = "{user.lastName.size}")
    private String lastName;

    @NotBlank(message = "{user.email.required}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.phone.required}")
    @Size(min = 10, max = 15, message = "{user.phone.size}")
    private String phone;
}