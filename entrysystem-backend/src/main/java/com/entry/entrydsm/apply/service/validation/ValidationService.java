package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.user.domain.User;

public interface ValidationService {
    ValidationResult validate(User user);
}
