package com.venly.venly.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WordDto {

    @NotNull(message = "firstWord Id is mandatory")
    @NotEmpty(message = "firstWord Id can not be empty")
    String firstWord;

    @NotNull(message = "secondWord Id is mandatory")
    @NotEmpty(message = "secondWord can not be empty")
    String secondWord;

    @NotNull(message = "relation Id is mandatory")
    @NotEmpty(message = "secondWord can not be empty")
    String relation;

}
