package com.whichbook.whichbook.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whichbook.whichbook.book.BookResponseDto;
import com.whichbook.whichbook.book.BookService;
import com.whichbook.whichbook.main.dto.DefaultSearchBookRequestDto;
import com.whichbook.whichbook.main.dto.DetailSearchBookRequestDto;
import com.whichbook.whichbook.main.vo.detail_search_response_vo.DetailSearchResponseVO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final BookService bookService;
    private final NaverApiService apiService;

    @GetMapping("/search")
    public ResponseEntity search(@Valid DefaultSearchBookRequestDto dto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity response = apiService.search_default(dto);

        response = Optional
                .of(response)
                .filter((res) -> res.getStatusCode().equals(HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

        return response;
    }


    @GetMapping("/search/detail/{title}")
    public ResponseEntity<?> retrieveBookListByTitle(@PathVariable String title){
        Set<BookResponseDto> bookResponseDtos = bookService.getBookListByTitle(title);
        return ResponseEntity.ok(bookResponseDtos);

//        ResponseEntity<String> response = apiService.search_detail(dto);
//
//        response = Optional
//                .of(response)
//                .filter((res) -> res.getStatusCode().equals(HttpStatus.OK))
//                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
//
//        JSONObject json = XML.toJSONObject(response.getBody());
//        DetailSearchResponseVO vo = null;
//        try {
//            vo = objectMapper
//                    .readValue(json.toString(), DetailSearchResponseVO.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return vo;
    }


}
