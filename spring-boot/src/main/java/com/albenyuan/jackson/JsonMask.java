package com.albenyuan.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JacksonAnnotationValue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Alben Yuan
 * @Date 2018-11-15 11:52
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonMask {


    public String mask() default "*";

    public int length() default -1;

    public int header() default -1;

    public int tail() default -1;


    public static class Vlaue implements JacksonAnnotationValue<JsonMask>, // since 2.6
            java.io.Serializable {
        @Override
        public Class<JsonMask> valueFor() {
            return null;
        }

        @Override
        public String toString() {
            return "";
        }
    }


}
