package com.whichbook.whichbook.book;

import java.util.Set;

public interface BookService {

    Set<BookResponseDto> getBookListByTitle(String title);
}
