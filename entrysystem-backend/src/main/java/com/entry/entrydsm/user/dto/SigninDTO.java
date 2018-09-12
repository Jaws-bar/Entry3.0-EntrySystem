package com.entry.entrydsm.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninDTO {
    private String email;
    private String password;
}
