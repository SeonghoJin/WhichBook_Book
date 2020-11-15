package com.whichbook.whichbook.api;

import com.whichbook.whichbook.main.dto.SearchBookRequestDto;
import org.springframework.http.ResponseEntity;

public interface ApiService {
    public ResponseEntity search(SearchBookRequestDto dto);
}
