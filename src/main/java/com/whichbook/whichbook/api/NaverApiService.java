package com.whichbook.whichbook.api;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import com.whichbook.whichbook.main.vo.SearchResponseVo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NaverApiService implements ApiService {
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Value("${naver.client_id}")
    private String client_id;

    @Value("${naver.client_secret}")
    private String client_secret;

    @Value("${naver.DetailSearchURL}")
    private String naverDetailSearchURL;

    public List<Book> search(SearchBookRequestDto dto){

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(String.valueOf(naverDetailSearchURL))
                .queryParam("d_titl", dto.getTitle())
                .queryParam("start", dto.getStart())
                .queryParam("display", dto.getDisplay())
                .queryParam("d_catg", dto.getD_catg())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", client_id);
        headers.set("X-Naver-Client-Secret", client_secret);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate
                .exchange(
                        String.valueOf(uriComponents),
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        return toBookList(response);
    }

    private List<Book> toBookList(ResponseEntity<String> response){

        JSONObject json = XML.toJSONObject(response.getBody());
        SearchResponseVo vo = modelMapper
                .map(json.toMap(), SearchResponseVo.class);

        Object item = vo.getRss().getChannel().getItem();
        int itemSize = Integer.parseInt(vo.getRss().getChannel().getTotal());

        List<Book> bookLists = new ArrayList<Book>();

        if(itemSize == 1){
            Book book = modelMapper.map(item, Book.class);
            bookLists.add(book);
        }
        else if(itemSize > 1){
            bookLists = modelMapper.map(item, new ArrayList<Book>().getClass());
            for(int i = 0; i < bookLists.size(); i++){
                bookLists.set(i, modelMapper.map(bookLists.get(i), Book.class));
            }
        }

        return bookLists;
    }


}
