package com.entry.entrydsm.apply.service.validation;

import com.entry.entrydsm.common.exception.RequestValidationException;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.entry.entrydsm.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class BaseValidationService {

    protected List<RestResponse.Error> validateClassification(User user) {
        try {
            ValidationUtil.validate(user);
        } catch (RequestValidationException e) {
            return ValidationUtil.toErrors(e.getValidationErrors());
        }

        return new ArrayList<>();
    }

    protected List<RestResponse.Error> validateInfo(User user) {
        try {
            ValidationUtil.validate(user.getInfo());
        } catch (RequestValidationException e) {
            return ValidationUtil.toErrors(e.getValidationErrors());
        }

        return new ArrayList<>();
    }

    protected List<RestResponse.Error> validateDocument(User user) {
        try {
            ValidationUtil.validate(user.getDocument());
        } catch (RequestValidationException e) {
            return ValidationUtil.toErrors(e.getValidationErrors());
        }

        return new ArrayList<>();
    }
}
