package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@Valid SearchBookRequestDto dto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Book> bookList = mainService.search(dto);

        return ResponseEntity.ok(bookList);
    }



}
