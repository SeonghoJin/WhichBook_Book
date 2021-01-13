package com.whichbook.whichbook.book;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String Isbn);
    List<Book> findAllByTitleContains(String title);
    List<Book> findAllByIsbn(String isbn);
    @Query("select book From Book book where book.title like %:title% and book.id > :id order by book.id")
    List<Book> findPageByTitle(@Param("title") String title, @Param("id") Long id, Pageable pageable);

}
