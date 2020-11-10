package com.whichbook.whichbook.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigure {
    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory =
                new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(3000);
        simpleClientHttpRequestFactory.setReadTimeout(3000);

        return new RestTemplate(simpleClientHttpRequestFactory);
    }
}
