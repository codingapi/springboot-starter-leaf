package com.sankuai.inf.leaf.server.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SegmentBufferView {
    private String key;
    private long value0;
    private int step0;
    private long max0;

    private long value1;
    private int step1;
    private long max1;
    private int pos;
    private boolean nextReady;
    private boolean initOk;

}
