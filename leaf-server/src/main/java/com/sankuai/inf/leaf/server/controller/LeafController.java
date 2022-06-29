package com.sankuai.inf.leaf.server.controller;

import com.sankuai.inf.leaf.server.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class LeafController {

    @ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
    @RestController
    static class SegmentController{

        @GetMapping("api/type")
        public String type(){
            return "SEGMENT";
        }
    }


    @ConditionalOnProperty(name = Constants.LEAF_SNOWFLAKE_ENABLE,havingValue = "true")
    @RestController
    static class SnowflakeController{

        @GetMapping("api/type")
        public String type(){
            return "SNOWFLAKE";
        }
    }

}
