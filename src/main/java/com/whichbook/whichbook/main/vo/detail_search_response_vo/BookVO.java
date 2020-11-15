package com.whichbook.whichbook.main.vo.detail_search_response_vo;

import com.whichbook.whichbook.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    String title;
    String link;
    String image;
    String author;
    String price;
    String discount;
    String publisher;
    String isbn;
    String description;
    Date pubdate;

    public Book toBook() {
        Book book = new Book();
        book.setTitle(title);
        book.setLink(link);
        book.setImage(image);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDiscount(discount);
        book.setPublisher(publisher);
        book.setPubdate(pubdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        book.setIsbn(isbn);
        book.setDescription(description);
        return book;
    }
}
