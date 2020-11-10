package com.whichbook.whichbook.domain;

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
    private Long id;

    @Column(unique = true)
    private String bookName;

}
