package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.service.BookService;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final BookService bookService;

    public MainService(@Qualifier("bookServiceImpl") BookService bookService) {
        this.bookService = bookService;
    }


    public List<Book> search(SearchBookRequestDto dto){
        //TODO 인증 절차

        List<Book> bookList = bookService.search(dto);

        return bookList;
    }
}
