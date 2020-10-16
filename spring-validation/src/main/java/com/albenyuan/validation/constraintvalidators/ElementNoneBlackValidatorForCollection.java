package com.albenyuan.validation.constraintvalidators;

import com.albenyuan.validation.constraints.ElementNoneBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class ElementNoneBlackValidatorForCollection implements ConstraintValidator<ElementNoneBlank, Collection<CharSequence>> {

    @Override
    public void initialize(ElementNoneBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(Collection<CharSequence> charSequences, ConstraintValidatorContext context) {

        if (null == charSequences || 0 == charSequences.size()) {
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
