package com.whichbook.whichbook.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class DefaultSearchBookRequestDto {

    @NotNull
    String query;

    String display = "10";

    String start = "1";


}
