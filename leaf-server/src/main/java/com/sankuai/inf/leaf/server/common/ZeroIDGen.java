package com.sankuai.inf.leaf.server.common;

import com.sankuai.inf.leaf.server.IDGen;

public class ZeroIDGen implements IDGen {

    @Override
    public Result get(String key) {
        return new Result(0, Status.SUCCESS);
    }

    @Override
    public boolean init() {
        return true;
    }
}
