package com.entry.entrydsm.grade.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class Score extends BaseTimeEntity {
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
    private Double conversionScore;

    @Column(length = 10)
    private Integer attendanceScore;

    @Column(nullable = false)
    private Double volunteerScore;

    @Column(nullable = false)
    private Double finalScore;

    public Score(User user, int attendanceScore) {
        this.user = user;
        this.volunteerScore = 0.0;
        this.attendanceScore = attendanceScore;
        this.conversionScore = 0.0;
        this.finalScore = 0.0;
    }

    public void setConversionScore(Double conversionScore) {
        this.conversionScore = conversionScore;
        syncFinalScore();
    }

    public void setAttendanceScore(Integer attendanceScore) {
        this.attendanceScore = attendanceScore;
        syncFinalScore();
    }

    public void setVolunteerScore(Double volunteerScore) {
        this.volunteerScore = volunteerScore;
        syncFinalScore();
    }

    private void syncFinalScore() {
        finalScore = conversionScore + attendanceScore + volunteerScore;
    }
}
