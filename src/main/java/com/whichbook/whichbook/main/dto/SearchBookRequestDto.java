package com.whichbook.whichbook.main.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class SearchBookRequestDto {
    String display = "20";

    String start = "1";

    @Value("${naver.book.catgory-it}")
    String d_catg;

    @NotEmpty
    String title;
}
