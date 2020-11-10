package com.whichbook.whichbook.book;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
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
