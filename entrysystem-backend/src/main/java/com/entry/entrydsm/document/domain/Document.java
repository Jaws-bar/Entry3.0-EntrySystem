package com.entry.entrydsm.document.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Document extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotNull
    @Length(max = 1600)
    @Column(length = 1600, nullable = false)
    private String introduce;

    @NotNull
    @Length(max = 1600)
    @Column(length = 1600, nullable = false)
    private String studyPlan;

    public Document(User user) {
        this.user = user;
        this.introduce = "";
        this.studyPlan = "";
    }

    public void update(Document document) {
        this.introduce = document.introduce;
        this.studyPlan = document.studyPlan;
    }
}
