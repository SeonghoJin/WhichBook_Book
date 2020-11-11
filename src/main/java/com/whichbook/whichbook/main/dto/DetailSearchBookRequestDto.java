package com.whichbook.whichbook.main.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class DetailSearchBookRequestDto {

    @NotNull
    String isbn;

}
