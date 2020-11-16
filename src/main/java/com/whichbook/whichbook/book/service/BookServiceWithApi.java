package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.api.ApiService;
import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.service.BookService;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import com.whichbook.whichbook.main.vo.SearchResponseVo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceWithApi implements BookService {

    private final ApiService apiService;
    private final BookRepository bookRepository;

    public List<Book> search(SearchBookRequestDto dto){
        List<Book> bookList = apiService.search(dto);

        List<Book> newBookList = bookList.stream().filter((book) -> {
            return !bookRepository.existsByIsbn(book.getIsbn());
        }).collect(Collectors.toList());

        bookRepository.saveAll(newBookList);

        return apiService.search(dto);
    }

}