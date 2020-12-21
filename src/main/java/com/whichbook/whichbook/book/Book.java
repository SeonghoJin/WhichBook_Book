package com.whichbook.whichbook.book;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "isbn")
public class Book {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String isbn;

    private String title;

    private String link;

    private String image;

    private String author;

    private String price;

    private String discount;

    private String publisher;

    private LocalDateTime pubdate;

    @Column(columnDefinition = "TEXT")
    private String description;

}
