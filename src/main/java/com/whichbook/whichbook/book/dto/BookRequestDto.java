package com.whichbook.whichbook.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class BookRequestDto {
    String display = "20";

    String start = "1";

    String d_catg = "280";

    @NotEmpty
    String title;
}
