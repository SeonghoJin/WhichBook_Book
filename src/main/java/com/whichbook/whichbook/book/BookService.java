package com.whichbook.whichbook.book;

import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import java.util.List;

public interface BookService {

    public List<Book> search(SearchBookRequestDto dto);

}

