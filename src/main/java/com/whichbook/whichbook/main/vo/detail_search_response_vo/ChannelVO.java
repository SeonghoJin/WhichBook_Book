package com.whichbook.whichbook.main.vo.detail_search_response_vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.whichbook.whichbook.main.vo.detail_search_response_vo.BookVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChannelVO {
    String title;
    String link;
    String description;
    String lastBuildDate;
    String total;
    String start;
    String display;
    @JacksonXmlElementWrapper(useWrapping = false)
    List<BookVO> item = new ArrayList<>();
}
