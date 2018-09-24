package com.entry.entrydsm.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SigninDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;

    public SigninDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
