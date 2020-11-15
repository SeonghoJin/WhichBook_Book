package com.whichbook.whichbook.main.vo;

import com.whichbook.whichbook.main.vo.ChannelVo;
import lombok.Data;

@Data
public class RssVo {
    String version;
    ChannelVo channel;
}
