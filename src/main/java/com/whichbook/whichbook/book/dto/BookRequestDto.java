package com.whichbook.whichbook.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class BookRequestDto {

    Long id = 0L;

    int display = 20;

    int start = 1;

    String d_catg = "280";

    @NotEmpty
    String title;
}
