package com.entry.entrydsm.grade.service;

import com.entry.entrydsm.common.exception.ValidationException;
import com.entry.entrydsm.grade.dto.GradeDTO;
import com.entry.entrydsm.grade.dto.GradeResponse;
import com.entry.entrydsm.user.domain.User;

public interface GradeService {

    GradeResponse get(User user);

    GradeResponse put(User user, GradeDTO dto) throws ValidationException;
}
