package com.venly.venly.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class WordDto {

    @NotNull(message = "firstWord Id is mandatory")
    @NotEmpty(message = "firstWord Id can not be empty")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "firstWord should contain only characters from 'a' to 'z' both lower and uppercase")
    String firstWord;

    @NotNull(message = "secondWord Id is mandatory")
    @NotEmpty(message = "secondWord can not be empty")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "secondWord should contain only characters from 'a' to 'z' both lower and uppercase")
    String secondWord;

    @NotNull(message = "relation Id is mandatory")
    @NotEmpty(message = "secondWord can not be empty")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "relation should contain only characters from 'a' to 'z' both lower and uppercase")
    String relation;

}
