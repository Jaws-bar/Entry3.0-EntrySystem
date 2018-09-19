package com.entry.entrydsm.grade.domain.graduate;

import java.util.Comparator;

public class GraduateGradeComparator implements Comparator<GraduateGrade> {
    @Override
    public int compare(GraduateGrade o1, GraduateGrade o2) {
        return o1.getId().getSemester().compareTo(o2.getId().getSemester());
    }
}
