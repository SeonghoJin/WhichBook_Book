package com.whichbook.whichbook.main.vo;

import lombok.Data;

@Data
public class ChannelVo {
    String title;
    String link;
    String description;
    String lastBuildDate;
    String total;
    String start;
    String display;
    Object item;
}
