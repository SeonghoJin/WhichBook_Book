package com.whichbook.whichbook.api;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.main.vo.BookVo;
import com.whichbook.whichbook.main.vo.RssVo;
import lombok.RequiredArgsConstructor;
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

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NaverApiService implements ApiService {
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Value("${naver.client_id}")
    private String clientId;

    @Value("${naver.client_secret}")
    private String clientSecret;

    @Value("${naver.DetailSearchURL}")
    private String naverDetailSearchURL;

    public List<Book> search(BookRequestDto dto){

        UriComponents components = UriComponentsBuilder
                .fromHttpUrl(String.valueOf(naverDetailSearchURL))
                .queryParam("d_titl", dto.getTitle())
                .queryParam("start", dto.getStart())
                .queryParam("display", dto.getDisplay())
                .queryParam("d_catg", dto.getD_catg())
                .encode(StandardCharsets.UTF_8)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", clientId);
        httpHeaders.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<RssVo> responseEntity =
                restTemplate.exchange(components.toUri(), HttpMethod.GET, entity, RssVo.class);

        return responseEntity.getBody()
                .getChannel().getItem().stream().map(BookVo::toBook).collect(Collectors.toList());
    }

}
