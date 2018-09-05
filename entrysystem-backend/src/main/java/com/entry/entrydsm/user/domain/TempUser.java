package com.entry.entrydsm.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class TempUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String code;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    public TempUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
