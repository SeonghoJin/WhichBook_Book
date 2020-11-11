package com.whichbook.whichbook.main;

import com.whichbook.whichbook.main.dto.DefaultSearchBookRequestDto;
import com.whichbook.whichbook.main.dto.DetailSearchBookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class NaverApiService {
    private final RestTemplate restTemplate;

    @Value("${naver.client_id}")
    private String client_id;

    @Value("${naver.client_secret}")
    private String client_secret;

    private String naverDefaultSearchURL = "https://openapi.naver.com/v1/search/book.json";
    private String naverDetailSearchURL = "https://openapi.naver.com/v1/search/book_adv.xml";

    public ResponseEntity search_default(DefaultSearchBookRequestDto dto){

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(String.valueOf(naverDefaultSearchURL))
                .queryParam("query", dto.getQuery())
                .queryParam("display", dto.getDisplay())
                .queryParam("start", dto.getStart())
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

        return response;
    }

    public ResponseEntity search_detail(DetailSearchBookRequestDto dto){

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(String.valueOf(naverDetailSearchURL))
                .queryParam("d_isbn", dto.getIsbn())
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

        return response;
    }

}
