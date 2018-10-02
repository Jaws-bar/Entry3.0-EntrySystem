package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.apply.dto.ValidationResult;
import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GedValidationService extends BaseValidationService implements ValidationService {
    @Override
    public ValidationResult validate(User user) {
        return ValidationResult.builder()
                .classificationErrors(validateClassification(user))
                .infoErrors(validateInfo(user))
                .gradeErrors(validateGrade(user))
                .documentErrors(validateDocument(user))
                .build();
    }

    public List<RestResponse.Error> validateGrade(User user) {
        try {
            ValidationUtil.validate(user.getGedScore());
        } catch (RequestValidationException e) {
            return ValidationUtil.toErrors(e.getValidationErrors());
        }
        return new ArrayList<>();
    }
}
