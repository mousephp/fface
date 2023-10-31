package com.fface.base.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordForm {
    @NotEmpty
    @Size(min = 5, max = 12)
    private String password;

    @NotEmpty
    @Size(min = 5, max = 12)
    private String confirmPassword;
}
