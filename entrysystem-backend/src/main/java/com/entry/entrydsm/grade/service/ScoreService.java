package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.grade.dto.ScoreResponse;
import com.entry.entrydsm.user.domain.GraduateType;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    public ScoreResponse getScore(User user) {
        if (user.getGraduateType() == GraduateType.GED) {
            return new ScoreResponse(user.getGraduateType(), user.getGedScore(), user.getApplyStatus().getReceiptCode());
        }
        return new ScoreResponse(user.getGraduateType(), user.getGraduateScore(), user.getApplyStatus().getReceiptCode());
    }
}
