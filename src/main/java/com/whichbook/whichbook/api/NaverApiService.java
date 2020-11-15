package com.whichbook.whichbook.api;

import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
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
public class NaverApiService implements ApiService {
    private final RestTemplate restTemplate;

    @Value("${naver.client_id}")
    private String client_id;

    @Value("${naver.client_secret}")
    private String client_secret;

    @Value("${naver.DetailSearchURL}")
    private String naverDetailSearchURL;

    public ResponseEntity<?> search(SearchBookRequestDto dto){

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

        return response;
    }

}
