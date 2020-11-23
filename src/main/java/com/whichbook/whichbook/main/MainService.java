package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import com.whichbook.whichbook.book.service.BookService;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final BookService bookService;

    public List<BookResponseDto> search(BookRequestDto dto){

        List<BookResponseDto> bookList = bookService.search(dto);

        return bookList;
    }
}
