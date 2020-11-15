package com.whichbook.whichbook.book;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Book {

    @Id @GeneratedValue
    long id;

    @Column(unique = true)
    private String isbn;

    private String title;

    private String link;

    private String image;

    private String author;

    private String price;

    private String discount;

    private String publisher;

    private String description;

    private LocalDateTime pubdate;

}
