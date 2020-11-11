package com.whichbook.whichbook.book;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id @GeneratedValue
    long id;

    @Column(unique = true)
    String isbn;

    String title;

    String link;

    String image;

    String author;

    String price;

    String discount;

    String publisher;

    String pubdate;

    String descripton;

}
