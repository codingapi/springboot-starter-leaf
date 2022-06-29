package com.sankuai.inf.leaf.server.service;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.IDGen;
import com.sankuai.inf.leaf.server.common.Result;
import com.sankuai.inf.leaf.server.exception.InitException;
import com.sankuai.inf.leaf.server.properties.LeafProperties;
import com.sankuai.inf.leaf.server.snowflake.SnowflakeIDGenImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service("SnowflakeService")
@ConditionalOnProperty(name = Constants.LEAF_SNOWFLAKE_ENABLE,havingValue = "true")
public class SnowflakeService {

    private final IDGen idGen;

    public SnowflakeService(LeafProperties leafProperties) throws InitException {
        String zkAddress = leafProperties.getZkAddress();
        int port = leafProperties.getPort();
        this.idGen = new SnowflakeIDGenImpl(zkAddress, port);
        if(idGen.init()) {
            log.info("Snowflake Service Init Successfully");
        } else {
            throw new InitException("Snowflake Service Init Fail");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }
}
