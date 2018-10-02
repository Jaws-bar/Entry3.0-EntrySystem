package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.user.domain.User;

import java.util.List;

public interface ValidationService {
    ValidationResult validate(User user);

    List<RestResponse.Error> validateGrade(User user);
}
