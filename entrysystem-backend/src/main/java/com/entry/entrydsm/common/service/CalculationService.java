package com.entry.entrydsm.common.service;

import com.entry.entrydsm.grade.domain.Score;
import com.entry.entrydsm.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface CalculationService {
    @Transactional
    Score calculate(User user);
}
