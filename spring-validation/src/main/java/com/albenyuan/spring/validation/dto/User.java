package com.albenyuan.spring.validation.dto;

import com.albenyuan.validation.constraints.ElementNoneEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
public class User {

    @NotBlank
    private String username;

    @NotEmpty
    private String description;

    @ElementNoneEmpty
    private List<String> tags;

}