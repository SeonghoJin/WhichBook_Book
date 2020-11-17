package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.api.ApiService;
import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceWithApi implements BookService {

    private final ApiService apiService;
    private final BookRepository bookRepository;

    public List<BookResponseDto> search(BookRequestDto dto){

        List<Book> booksInApi = apiService.search(dto);

        booksInApi.stream().forEach(book -> {
            if(!bookRepository.existsByIsbn(book.getIsbn())){
                bookRepository.save(book);
            }
        });

        return booksInApi.stream().map(BookResponseDto::of).collect(Collectors.toList());
    }

}