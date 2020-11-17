package com.whichbook.whichbook.main;

import com.whichbook.whichbook.book.Book;
import com.whichbook.whichbook.book.BookRepository;
import com.whichbook.whichbook.book.dto.BookRequestDto;
import com.whichbook.whichbook.book.dto.BookResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Autowired
    MainService mainService;

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("검색 테스트 - 성공")
    public void searchTestUsingMainService () throws Exception{
        String titl = "이것이 취업을 위한 코딩 테스트다 with 파이썬";
        BookRequestDto dto = new BookRequestDto();
        dto.setTitle(titl);

        List<BookResponseDto> bookList = mainService.search(dto);
        assertThat(bookList).isNotEmpty();
    }


    @Test
    @DisplayName("검색 테스트 - 성공(결과 하나일 경우)")
    public void successSearchTestWithSingleResult () throws Exception{
        String titl = "이것이 취업을 위한 코딩 테스트다 with 파이썬";
        mockmvc.perform(get("/search")
                .param("title", titl))
                .andExpect(status().isOk());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).isNotEmpty();
    }

    @Test
    @DisplayName("검색 테스트 - 성공(결과 여러개일 경우)")
    public void successSearchTestWithMultiResult () throws Exception{
        mockmvc.perform(get("/search")
                .param("title", "saas"))
                .andExpect(status().isOk());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).isNotEmpty();
    }


    @Test
    @DisplayName("검색 테스트 - 실패 (서버 내부 에러)")
    public void failDetailSearchTestWithInternalSeverError () throws Exception{
        try {
            mockmvc.perform(get("/search")
                    .param("sardsfas", "sadd"));
        } catch (NestedServletException e){
            e.getCause();
        }
    }
}

