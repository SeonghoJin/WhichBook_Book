package com.whichbook.whichbook.book;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String Isbn);
    List<Book> findAllByTitleContains(String title);
}
