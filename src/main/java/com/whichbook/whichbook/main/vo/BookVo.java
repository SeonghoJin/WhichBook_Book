package com.whichbook.whichbook.main.vo;

import com.whichbook.whichbook.book.Book;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class BookVo {

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
