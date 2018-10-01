package com.entry.entrydsm.common.service;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.domain.ged.GedScore;
import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GedCalculationService implements CalculationService {

    private static final int GED_ATTENDANCE_SCORE = 15;

    @Override
    @Transactional
    public Score calculate(User user) {
        GedScore score = user.getGedScore();
        score.setVolunteerScore(calculateVolunteerScore(score));
        score.setAttendanceScore(GED_ATTENDANCE_SCORE);
        score.setConversionScore(calculateConversionScore(score) * (user.getAdmission() == Admission.MEISTER ? 90 : 150));
        return score;
    }

    private double calculateConversionScore(GedScore score) {
        return (score.getGrade() - 50) / 50;
    }

    private double calculateVolunteerScore(GedScore score) {
        return 3 + (score.getGrade() - 40) / 60 * 12;
    }


}
