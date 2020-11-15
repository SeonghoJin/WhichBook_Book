package com.whichbook.whichbook.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    public void SuccessFindByTitleLike(){
        Book book = Book.builder()
                        .title("아마존 웹 서비스, 진성호")
                        .isbn("123123123 1231231")
                        .build();

        repository.save(book);
        List<Book> oldBook = repository.findAllByTitleContains("웹");
        System.out.println(oldBook);
        Assertions.assertThat(oldBook).isNotEmpty();

    }
}