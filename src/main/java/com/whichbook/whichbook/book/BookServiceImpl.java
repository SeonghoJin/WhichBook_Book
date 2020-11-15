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

        String title = dto.getTitle();

        List<Book> bookList = bookRepository.findAllByTitleContains(title);

        if(!bookList.isEmpty()){
            return bookList;
        }

        bookList = searchUsingApiService(dto);
        for(int i = 0; i < bookList.size(); i++){
            Book book = bookList.get(i);
            String isbn = book.getIsbn();
            if(!bookRepository.existsByIsbn(isbn)){
                bookRepository.save(book);
            }
        }

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

        if(itemSize == 0){
            return bookLists;
        }
        else if(itemSize == 1){
            Book book = modelMapper.map(item, Book.class);
            bookLists.add(book);
        }
        else{
            bookLists = modelMapper.map(item, new ArrayList<Book>().getClass());

//            bookLists.stream().map((book) -> {
//                return modelMapper.map(book,Book.class);
//            }).collect(Collectors.toList());
            for(int i = 0; i < bookLists.size(); i++){
                bookLists.set(i, modelMapper.map(bookLists.get(i), Book.class));
            }
        }

        return bookLists;
    }
}
