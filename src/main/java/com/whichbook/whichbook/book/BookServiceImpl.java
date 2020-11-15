package com.whichbook.whichbook.book;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookProvider bookProvider;
    private final BookRepository bookRepository;

    BookServiceImpl(BookProvider bookProvider, BookRepository bookRepository) {
        this.bookProvider = bookProvider;
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<BookResponseDto> getBookListByTitle(String title) {
        Set<BookResponseDto> books = bookRepository.findAllByTitleContains(title)
                .stream().map(BookResponseDto::of).collect(Collectors.toSet());
        bookProvider.searchBookByTitle(title);

        books.addAll(bookRepository.saveAll(bookProvider.searchBookByTitle(title))
                .stream().map(BookResponseDto::of).collect(Collectors.toSet()));

        return books;
    }
}
