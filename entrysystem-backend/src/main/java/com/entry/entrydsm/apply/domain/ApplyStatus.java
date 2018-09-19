package com.entry.entrydsm.apply.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ApplyStatus extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private Boolean finalSubmit;

    @Column(nullable = false)
    private Boolean passStatus;

    @Column(nullable = false)
    private Boolean payment;

    @Column(nullable = false)
    private Boolean receipt;

    public ApplyStatus(User user) {
        this.user = user;
        this.finalSubmit = false;
        this.passStatus = false;
        this.payment = false;
        this.receipt = false;
    }
}
