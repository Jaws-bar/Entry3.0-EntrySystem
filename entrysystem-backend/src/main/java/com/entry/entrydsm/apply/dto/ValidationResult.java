package com.entry.entrydsm.apply.dto;

import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.validate.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class ValidationResult {
    private final List<RestResponse.Error> classificationErrors;
    private final List<RestResponse.Error> infoErrors;
    private final List<RestResponse.Error> gradeErrors;
    private final List<RestResponse.Error> documentErrors;

    public ValidationResult(List<RestResponse.Error> classificationErrors, List<RestResponse.Error> infoErrors, List<RestResponse.Error> gradeErrors, List<RestResponse.Error> documentErrors) {
        this.classificationErrors = classificationErrors;
        this.infoErrors = infoErrors;
        this.gradeErrors = gradeErrors;
        this.documentErrors = documentErrors;
    }


    @JsonGetter("isClassificationValid")
    public boolean isClassificationValid() {
        return this.classificationErrors.isEmpty();
    }

    @JsonGetter("isInfoValid")
    public boolean isInfoValid() {
        return this.infoErrors.isEmpty();
    }

    @JsonGetter("isGradeValid")
    public boolean isGradeValid() {
        return this.gradeErrors.isEmpty();
    }

    @JsonGetter("isDocumentValid")
    public boolean isDocumentValid() {
        return this.documentErrors.isEmpty();
    }

    @JsonGetter("isValid")
    public boolean isValid() {
        return isClassificationValid() && isInfoValid() && isGradeValid() && isDocumentValid();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<RestResponse.Error> classificationErrors;
        private List<RestResponse.Error> infoErrors;
        private List<RestResponse.Error> gradeErrors;
        private List<RestResponse.Error> documentErrors;

        private Builder() {
            this.classificationErrors = new ArrayList<>();
            this.infoErrors = new ArrayList<>();
            this.gradeErrors = new ArrayList<>();
            this.documentErrors = new ArrayList<>();
        }

        public Builder classificationErrors(Set<ConstraintViolation<Object>> errors) {
            return classificationErrors(ValidationUtil.toErrors(errors));
        }

        public Builder classificationErrors(List<RestResponse.Error> errors) {
            this.classificationErrors = errors;
            return this;
        }

        public Builder infoErrors(Set<ConstraintViolation<Object>> errors) {
            return infoErrors(ValidationUtil.toErrors(errors));
        }

        public Builder infoErrors(List<RestResponse.Error> errors) {
            this.infoErrors = errors;
            return this;
        }

        public Builder gradeErrors(Set<ConstraintViolation<Object>> errors) {
            return gradeErrors(ValidationUtil.toErrors(errors));
        }

        public Builder gradeErrors(List<RestResponse.Error> errors) {
            this.gradeErrors = errors;
            return this;
        }

        public Builder documentErrors(Set<ConstraintViolation<Object>> errors) {
            return documentErrors(ValidationUtil.toErrors(errors));
        }

        public Builder documentErrors(List<RestResponse.Error> errors) {
            this.documentErrors = errors;
            return this;
        }

        public ValidationResult build() {
            return new ValidationResult(classificationErrors, infoErrors, gradeErrors, documentErrors);
        }
    }


}
