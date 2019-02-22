package com.cnuip.colligate.vo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteClass {
    private long timestamp;
    @JsonProperty("from_client")
    private String fromClient;
    private String msgId;
}
