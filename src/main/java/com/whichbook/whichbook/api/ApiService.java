package com.whichbook.whichbook.api;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.dto.BookRequestDto;

import java.util.List;

public interface ApiService {
    public List<Book> search(BookRequestDto dto);
}
