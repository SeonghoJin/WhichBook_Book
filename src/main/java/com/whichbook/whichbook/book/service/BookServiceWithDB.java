package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceWithDB implements BookService {

    private final BookRepository bookRepository;

    public List<BookResponseDto> search(BookRequestDto dto){
        List<Book> bookList = bookRepository.findAllByTitleContains(dto.getTitle());
        return bookList
                .stream().map(BookResponseDto::of).collect(Collectors.toList());
    }


}
