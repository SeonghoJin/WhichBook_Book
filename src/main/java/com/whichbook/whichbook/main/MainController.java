package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/book/search")
    public ResponseEntity<?> search(@Valid BookRequestDto dto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<BookResponseDto> bookList = mainService.search(dto);

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getBookInfo(@PathVariable Long bookId) {
        BookResponseDto responseDto = mainService.findBookById(bookId);
        return ResponseEntity.ok(responseDto);
    }
}
