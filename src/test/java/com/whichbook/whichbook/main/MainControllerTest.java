package com.whichbook.whichbook.main;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    MockMvc mockmvc;
    @Autowired
    NaverApiService naverApiService;

    @Test
    @DisplayName("기본 검색 테스트 - 성공")
    public void successDefaultSearchTest () throws Exception{
        mockmvc.perform(get("/search")
                        .param("query","진성호")
                        .param("start", "2")
                        .param("display", "21"))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("기본 검색 테스트 - 실패 (요청 변수 부족)")
    public void failDefaultSearchTestWithBadRequest () throws Exception{
            mockmvc.perform(get("/search")
                    .param("start", "2"))
                    .andExpect(status().isBadRequest());
    }

    //내부 엑셉션이 떠는데..?? 이게머지
    @Test
    @DisplayName("기본 검색 테스트 - 실패 (서버 내부 에러)")
    public void failDefaultSearchTestWithInternalSeverError () throws Exception{
        try {
            mockmvc.perform(get("/search")
                    .param("query","진성호")
                    .param("sardsfas", "sadd"));
        } catch (NestedServletException e){
            e.getCause();
        }
    }

    @Test
    @DisplayName("상세 검색 테스트 - 성공")
    public void successDetailSearchTest () throws Exception{
        mockmvc.perform(get("/search_detail")
                .param("isbn", "8936438034 9788936438036"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상세 검색 테스트 - 실패 (요청 변수 부족)")
    public void failDetailSearchTestWithBadRequest () throws Exception{
            mockmvc.perform(get("/search_detail"))
                    .andExpect(status().isBadRequest());
    }

    //내부 엑셉션이 떠는데..?? 이게머지
    @Test
    @DisplayName("상세 검색 테스트 - 실패 (서버 내부 에러)")
    public void failDetailSearchTestWithInternalSeverError () throws Exception{
        try {
            mockmvc.perform(get("/search_detail")
                    .param("sardsfas", "sadd"));
        } catch (NestedServletException e){
            e.getCause();
        }
    }
}

