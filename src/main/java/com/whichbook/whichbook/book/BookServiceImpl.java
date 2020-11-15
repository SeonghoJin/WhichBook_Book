package com.whichbook.whichbook.book;

import com.whichbook.whichbook.api.ApiService;
import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import com.whichbook.whichbook.main.vo.SearchResponseVo;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final ApiService apiService;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public List<Book> search(SearchBookRequestDto dto){

        List<Book> bookList = bookRepository.findAllByTitleContains(dto.getTitle());

        if(!bookList.isEmpty()){
            return bookList;
        }

        bookList = searchUsingApiService(dto);

        List<Book> newBookList = bookList.stream().filter((book) -> {
            return !bookRepository.existsByIsbn(book.getIsbn());
        }).collect(Collectors.toList());
        bookRepository.saveAll(newBookList);

        return bookList;
    }


    private List<Book> searchUsingApiService(SearchBookRequestDto dto){
        ResponseEntity<String> response = apiService.search(dto);

        if(!response.getStatusCode().equals(HttpStatus.OK)){
            return new ArrayList<Book>();
        }

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
