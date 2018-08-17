package com.entry.entrydsm.tempuser.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class TempUser {

    @Id
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, unique = true, length = 32)
    private String code;

    @Builder
    public TempUser(String email, String password, String code) {
        this.email = email;
        this.password = password;
        this.code = code;
    }
}
