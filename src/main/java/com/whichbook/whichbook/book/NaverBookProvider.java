package com.whichbook.whichbook.book;

import com.whichbook.whichbook.main.vo.detail_search_response_vo.BookVO;
import com.whichbook.whichbook.main.vo.detail_search_response_vo.DetailSearchResponseVO;
import com.whichbook.whichbook.main.vo.detail_search_response_vo.RSSVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "naver")
public class NaverBookProvider implements BookProvider{

    private final RestTemplate restTemplate;

    @Value("${naver.client_id}")
    private String clientId;
    @Value("${naver.client_secret}")
    private String clientSecret;
    @Value("${naver.default.search-url}")
    private String defaultSearchUrl;
    @Value("${naver.detail.search-url}")
    private String detailSearchUrl;
    @Value("naver.book.catgory-it")
    private String catg;

    public NaverBookProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Book> searchBookByTitle(String title) {

        UriComponents components = UriComponentsBuilder
                .fromHttpUrl(detailSearchUrl)
                .queryParam("d_titl", title)
                .queryParam("d_catg", catg).build()
                .encode(StandardCharsets.UTF_8);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", clientId);
        httpHeaders.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<RSSVO> responseEntity =
                restTemplate.exchange(components.toUri(), HttpMethod.GET, entity, RSSVO.class);

        return responseEntity.getBody()
                .getChannel().getItem().stream().map(BookVO::toBook).collect(Collectors.toList());
    }
}
