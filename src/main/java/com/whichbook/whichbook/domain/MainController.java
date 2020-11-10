package com.whichbook.whichbook.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private String client_id = "GKnxP64ihl_zb0MLDare";
    private String client_secret = "o6E_ialqKv";

//    @GetMapping("/search")
//    public ResponseEntity search(String SearchWord){
//
//        /*try {
//            String endcodedSearchWord = URLEncoder.encode(SearchWord, "utf-8");
//            URL url = new URL(
//                    "https://openapi.naver.com/v1/search/book.json?query="+endcodedSearchWord+"&display=10");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("X-Naver-Client-Id", client_id);
//            con.setRequestProperty("X-Naver-Client-Secret", client_secret);
//
//            int responseCode = con.getResponseCode();
//            Charset charset = Charset.forName("UTF-8");
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            Request
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//
//        try {
//            String endcodedSearchWord = URLEncoder.encode(SearchWord, "utf-8");
//            URL url = new URL(
//                    "https://openapi.naver.com/v1/search/book.json?query="+endcodedSearchWord+"&display=10");
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.valueOf(url))
//                    .queryParam("query", endcodedSearchWord)
//                    .queryParam(restTemplate.getForEntity(url,)
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}
