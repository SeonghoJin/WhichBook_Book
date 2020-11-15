package com.whichbook.whichbook.main.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class SearchBookRequestDto {
    String display = "20";

    String start = "1";

    String d_catg = "280";

    @NotEmpty
    String title;
}
