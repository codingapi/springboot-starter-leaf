package com.sankuai.inf.leaf.server;

import com.sankuai.inf.leaf.server.common.Result;

public interface IDGen {

    Result get(String key);

    boolean init();

}
