package com.albenyuan.validation.constraintvalidators;

import com.albenyuan.validation.constraints.ElementNoneBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ElementNoneBlackValidatorForArray implements ConstraintValidator<ElementNoneBlank, CharSequence[]> {

    @Override
    public void initialize(ElementNoneBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence[] charSequences, ConstraintValidatorContext context) {

        if (null == charSequences || 0 == charSequences.length) {
            return false;
        }
        for (CharSequence charSequence : charSequences) {
            if (null == charSequence) {
                return false;
            }
            if (0 == charSequence.toString().trim().length()) {
                return false;
            }
        }
        return true;
    }
}
