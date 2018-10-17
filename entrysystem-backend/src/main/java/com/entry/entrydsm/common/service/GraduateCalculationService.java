package com.entry.entrydsm.common.service;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.grade.domain.graduate.GraduateGrade;
import com.entry.entrydsm.grade.domain.graduate.GraduateScore;
import com.entry.entrydsm.info.domain.Admission;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GraduateCalculationService implements CalculationService {

    private static final int GRADE_1_SEMESTER_1 = 0;
    private static final int GRADE_1_SEMESTER_2 = 1;
    private static final int GRADE_2_SEMESTER_1 = 2;
    private static final int GRADE_2_SEMESTER_2 = 3;
    private static final int GRADE_3_SEMESTER_1 = 4;
    private static final int GRADE_3_SEMESTER_2 = 5;

    @Override
    @Transactional
    public Score calculate(User user) {
        GraduateScore graduateScore = user.getGraduateScore();

        calculateGrade(user);
        graduateScore.setVolunteerScore(calculateVolunteerScore(graduateScore));
        graduateScore.setAttendanceScore(calculateAttendanceScore(graduateScore));

        return graduateScore;
    }

    private int calculateAttendanceScore(GraduateScore graduateScore) {
        int totalAbsenceCount = getTotalAbsenceCount(graduateScore);
        if (totalAbsenceCount >= 15) {
            return 0;
        }
        return 15 - totalAbsenceCount;
    }

    private int getTotalAbsenceCount(GraduateScore graduateScore) {
        return graduateScore.getFullCut() +
                (graduateScore.getEarlyLeave() + graduateScore.getLate() + graduateScore.getPeriodCut()) / 3;
    }

    private double calculateVolunteerScore(GraduateScore graduateScore) {
        int volunteerTime = graduateScore.getVolunteerTime();
        if (volunteerTime <= 14) {
            return 3;
        }
        if (volunteerTime >= 50) {
            return 15;
        }
        return 3 + (volunteerTime - 14) / 36.0 * 12;
    }

    private void calculateGrade(User user) {
        List<GraduateGrade> grades = user.getGrades();
        GraduateScore graduateScore = user.getGraduateScore();

        if (isSkippedFirstAndSecondGrade(grades)) {
            calculateWithoutFirstAndSecondGrade(user);
            return;
        }

        if (isSkippedFirstGrade(grades)) {
            calculateWithoutFirstGrade(user);
            return;
        }

        if (isSkippedSecondGrade(grades)) {
            calculateWithoutSecondGrade(user);
            return;
        }

        final double multiplyNotThird = getMultiply(user.getAdmission(), false);
        final double multiplyThird = getMultiply(user.getAdmission(), true);

        graduateScore.setFirstGrade(calculateFirstGradeScore(grades) * multiplyNotThird);
        graduateScore.setSecondGrade(calculateSecondGradeScore(grades) * multiplyNotThird);
        graduateScore.setThirdGrade(calculateThirdGradeScore(grades, user.getGraduateType()) * multiplyThird);
    }

    private void calculateWithoutSecondGrade(User user) {
        final double multiplyNotThird = getMultiply(user.getAdmission(), false);
        final double multiplyThird = getMultiply(user.getAdmission(), true);
        final double firstGradeScore = calculateFirstGradeScore(user.getGrades());
        final double thirdGradeScore = calculateThirdGradeScore(user.getGrades(), user.getGraduateType());
        GraduateScore graduateScore = user.getGraduateScore();
        graduateScore.setFirstGrade(firstGradeScore * multiplyNotThird);
        graduateScore.setSecondGrade(
                calculateMissingGradeScore(firstGradeScore, thirdGradeScore, user.getGraduateType()) * multiplyNotThird);
        graduateScore.setThirdGrade(thirdGradeScore * multiplyThird);
    }

    private void calculateWithoutFirstGrade(User user) {
        final double multiplyNotThird = getMultiply(user.getAdmission(), false);
        final double multiplyThird = getMultiply(user.getAdmission(), true);
        final double thirdGradeScore = calculateThirdGradeScore(user.getGrades(), user.getGraduateType());
        final double secondGradeScore = calculateSecondGradeScore(user.getGrades());
        GraduateScore graduateScore = user.getGraduateScore();
        graduateScore.setFirstGrade(
                calculateMissingGradeScore(secondGradeScore, thirdGradeScore, user.getGraduateType()) * multiplyNotThird);
        graduateScore.setSecondGrade(secondGradeScore * multiplyNotThird);
        graduateScore.setThirdGrade(thirdGradeScore * multiplyThird);
    }

    private void calculateWithoutFirstAndSecondGrade(User user) {
        final double multiplyNotThird = getMultiply(user.getAdmission(), false);
        final double multiplyThird = getMultiply(user.getAdmission(), true);
        final double thirdGradeScore = calculateThirdGradeScore(user.getGrades(), user.getGraduateType());
        GraduateScore graduateScore = user.getGraduateScore();
        graduateScore.setFirstGrade(thirdGradeScore * multiplyNotThird);
        graduateScore.setSecondGrade(thirdGradeScore * multiplyNotThird);
        graduateScore.setThirdGrade(thirdGradeScore * multiplyThird);
    }

    private double getMultiply(Admission admissionType, boolean isThirdGrade) {
        return admissionType != Admission.NORMAL
                ? isThirdGrade ? 3.6 : 2.7
                : isThirdGrade ? 6 : 4.5;
    }

    private double calculateFirstGradeScore(List<GraduateGrade> grades) {
        return calculateAnnualScore(grades.get(GRADE_1_SEMESTER_1), grades.get(GRADE_1_SEMESTER_2));
    }

    private boolean isSkippedSecondGrade(List<GraduateGrade> grades) {
        return isYearSkipped(grades.get(GRADE_2_SEMESTER_1), grades.get(GRADE_2_SEMESTER_2));
    }

    private double calculateMissingGradeScore(double otherGradeScore, double thirdGradeScore, GraduateType graduateType) {
        if (graduateType == GraduateType.WILL) {
            return (otherGradeScore + thirdGradeScore / 2) * 2 / 3;
        }
        return (otherGradeScore + thirdGradeScore) / 2;
    }

    private double calculateSecondGradeScore(List<GraduateGrade> grades) {
        return calculateAnnualScore(grades.get(GRADE_2_SEMESTER_1), grades.get(GRADE_2_SEMESTER_2));
    }

    private boolean isSkippedFirstGrade(List<GraduateGrade> grades) {
        return isYearSkipped(grades.get(GRADE_1_SEMESTER_1), grades.get(GRADE_1_SEMESTER_2));
    }

    private double calculateThirdGradeScore(List<GraduateGrade> grades, GraduateType graduateType) {
        if (graduateType == GraduateType.WILL) {
            return calculateAnnualScore(grades.get(GRADE_3_SEMESTER_1));
        }
        return calculateAnnualScore(grades.get(GRADE_3_SEMESTER_1), grades.get(GRADE_3_SEMESTER_2));
    }

    private boolean isSkippedFirstAndSecondGrade(List<GraduateGrade> grades) {
        return isYearSkipped(grades.get(GRADE_1_SEMESTER_1), grades.get(GRADE_1_SEMESTER_2))
                && isYearSkipped(grades.get(GRADE_2_SEMESTER_1), grades.get(GRADE_2_SEMESTER_2));
    }

    private boolean isYearSkipped(GraduateGrade semester1, GraduateGrade semester2) {
        return semester1.isSkipped() && semester2.isSkipped();
    }

    private double calculateAnnualScore(GraduateGrade semester1, GraduateGrade semester2) {
        if (isYearSkipped(semester1, semester2)) {
            return 0;
        }

        if (semester1.isSkipped()) {
            return semester2.calculateScore() * 2;
        }

        if (semester2.isSkipped()) {
            return semester1.calculateScore() * 2;
        }

        return semester1.calculateScore() + semester2.calculateScore();
    }

    private double calculateAnnualScore(GraduateGrade semester) {
        return semester.calculateScore() * 2;
    }


}
