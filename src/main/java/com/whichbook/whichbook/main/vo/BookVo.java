package com.whichbook.whichbook.main.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookVo {

    String title;
    String link;
    String image;
    String author;
    String price;
    String discount;
    String publisher;
    LocalDateTime pubdate;
    String isbn;
    String description;

}
