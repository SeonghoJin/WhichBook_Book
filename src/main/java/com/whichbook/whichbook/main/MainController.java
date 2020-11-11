package com.whichbook.whichbook.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whichbook.whichbook.main.dto.DefaultSearchBookRequestDto;
import com.whichbook.whichbook.main.dto.DetailSearchBookRequestDto;
import com.whichbook.whichbook.main.vo.detail_search_response_vo.DetailSearchResponseVO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final NaverApiService apiService;
    private final ObjectMapper objectMapper;
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


    @GetMapping("/search_detail")
    public DetailSearchResponseVO search_detail(@Valid DetailSearchBookRequestDto dto, Errors errors){

//        if(errors.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }

        ResponseEntity<String> response = apiService.search_detail(dto);

        response = Optional
                .of(response)
                .filter((res) -> res.getStatusCode().equals(HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

        JSONObject json = XML.toJSONObject(response.getBody());
        DetailSearchResponseVO vo = null;
        try {
            vo = objectMapper
                    .readValue(json.toString(), DetailSearchResponseVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vo;
    }


}
