package com.albenyuan.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element must be a {@code Collection}, Array.
 * And each item in the element must not be {@code null} nor empty.
 * Supported types are:
 * <ul>
 *  <li>{@code CharSequence} (length of character sequence is evaluated)</li>
 *  <li>{@code Collection} (collection size is evaluated)</li>
 *  <li>{@code Map} (map size is evaluated)</li>
 *  <li>Array (array length is evaluated)</li>
 * </ul>
 */
@Documented
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Repeatable(ElementNoneEmpty.List.class)
public @interface ElementNoneEmpty {


    String message() default "{javax.validation.constraints.ItemNoneEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link ElementNoneEmpty} constraints on the same element.
     *
     * @see ElementNoneEmpty
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        ElementNoneEmpty[] value();
    }
}
