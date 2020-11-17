package com.whichbook.whichbook.book.service;

import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    public List<BookResponseDto> search(BookRequestDto dto);

}

