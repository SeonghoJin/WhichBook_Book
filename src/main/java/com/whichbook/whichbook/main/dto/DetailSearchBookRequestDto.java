package com.whichbook.whichbook.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DetailSearchBookRequestDto {

    @NotNull
    String isbn;

}
