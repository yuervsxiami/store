package com.cnuip.colligate.vo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Record {

    @JsonProperty("msg-id")
    private String msgId;

    @JsonProperty("conv-id")
    private String convId;

    private String from;

    private Date timestamp;

    @JsonProperty("is-room")
    private String isRoom;

    private String to;

    private String data;

    @JsonProperty("is-conv")
    private boolean isConv;

    private PushModel pushModel;
}
