package com.albenyuan.spring.batch.model;

import lombok.ToString;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

import java.util.Map;

@ToString
public class SchemaResourceItem implements ResourceAware {

    private Resource resource;

    private Map<String, String> values;

    private String value;

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
