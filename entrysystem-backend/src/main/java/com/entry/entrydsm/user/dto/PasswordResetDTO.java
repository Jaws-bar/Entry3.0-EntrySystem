package com.entry.entrydsm.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class PasswordResetDTO {
    @NotNull
    @Email(regexp = "([\\w-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{5}$")
    private String passwordResetCode;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$")
    private String password;
}
