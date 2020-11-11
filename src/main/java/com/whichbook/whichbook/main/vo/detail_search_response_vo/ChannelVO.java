package com.whichbook.whichbook.main.vo.detail_search_response_vo;

import com.whichbook.whichbook.main.vo.detail_search_response_vo.BookVO;
import lombok.Data;

@Data
public class ChannelVO {
    String title;
    String link;
    String description;
    String lastBuildDate;
    String total;
    String start;
    String display;
    BookVO item;
}
