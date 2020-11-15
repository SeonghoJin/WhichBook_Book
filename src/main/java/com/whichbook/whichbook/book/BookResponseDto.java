package com.whichbook.whichbook.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "isbn")
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private String title;
    private String isbn;
    private String author;
    private String price;
    private String description;
    private LocalDateTime pubdate;


    public static BookResponseDto of(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setIsbn(book.getIsbn());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setPrice(book.getPrice());
        bookResponseDto.setDescription(book.getDescription());
        bookResponseDto.setPubdate(book.getPubdate());

        return bookResponseDto;
    }
}
