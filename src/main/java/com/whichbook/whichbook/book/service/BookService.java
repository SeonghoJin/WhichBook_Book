package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import java.util.List;

public interface BookService {

    public List<Book> search(SearchBookRequestDto dto);

}

