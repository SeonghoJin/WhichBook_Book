package com.whichbook.whichbook.main.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChannelVo {
    String title;
    String link;
    String description;
    String lastBuildDate;
    String total;
    String start;
    String display;
    @JacksonXmlElementWrapper(useWrapping = false)
    List<BookVo> item = new ArrayList<>();
}
