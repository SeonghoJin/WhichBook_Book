package com.whichbook.whichbook.book;

import java.util.List;

public interface BookProvider {
    List<Book> searchBookByTitle(String title);
}
