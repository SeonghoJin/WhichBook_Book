package com.whichbook.whichbook.api;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiService {
    public List<Book> search(SearchBookRequestDto dto);
}
