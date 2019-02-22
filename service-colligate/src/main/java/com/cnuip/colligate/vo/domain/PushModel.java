package com.cnuip.colligate.vo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushModel {
    private String alert;
    private String category;
    private String itemId;
    private boolean pushBag;
    private boolean pushTreasure;
    private String title;
    private String remoteKey;
}
