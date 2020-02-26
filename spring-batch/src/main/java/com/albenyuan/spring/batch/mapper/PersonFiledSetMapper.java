package com.albenyuan.spring.batch.mapper;

import com.albenyuan.spring.batch.domain.Person;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @Author Alben Yuan
 * @Date 2019-04-21 22:00
 */
public class PersonFiledSetMapper implements FieldSetMapper<Person> {

    @Override
    public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        return null;
    }
}
