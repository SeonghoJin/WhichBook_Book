package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.api.ApiService;
import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookNotFoundException;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceWithApi implements BookService {

    private final ApiService apiService;
    private final BookRepository bookRepository;

    public List<BookResponseDto> search(BookRequestDto dto){
        //전부다 참조하는게 아닌, 몇개만 가져와야한다.
        List<Book> books = bookRepository.findAllByTitleContains(dto.getTitle());
        List<Book> booksInApi = apiService.search(dto);

        booksInApi.stream()
                .filter((book) -> !books.contains(book))
                .filter((book) -> !bookRepository.existsByIsbn(book.getIsbn()))
                .map(bookRepository::save).collect(Collectors.toList());

        booksInApi = booksInApi.stream().map((book -> {
            List<Book> books1 = bookRepository.findAllByIsbn(book.getIsbn());
            if(books1.size() != 0){
            Long id = books1.get(0).getId();
            book.setId(id);}
            return book;
        })).collect(Collectors.toList());

        return booksInApi.stream().map(BookResponseDto::of).collect((Collectors.toList()));
    }

    @Override
    public BookResponseDto findById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId + "에 해당하는 책이 없습니다."));
        return BookResponseDto.of(book);
    }

}