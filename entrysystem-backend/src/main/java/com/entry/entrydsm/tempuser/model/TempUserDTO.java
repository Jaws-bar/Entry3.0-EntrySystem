package com.entry.entrydsm.tempuser.model;

import com.entry.entrydsm.tempuser.model.TempUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TempUserDTO {
    public String email;
    public String password;
    public String code;

    public TempUser toEntity() {
        return TempUser.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .code(this.getCode())
                .build();
    }
}
