package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.BookService;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final BookService bookService;

    public List<Book> search(SearchBookRequestDto dto){
        //인증 절차

        List<Book> bookList = bookService.search(dto);

        return bookList;
    }
}
