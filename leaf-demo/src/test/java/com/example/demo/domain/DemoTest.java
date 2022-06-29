package com.example.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lorne
 * @since 1.0.0
 */
@SpringBootTest
@Slf4j
class DemoTest {

    @Test
    void test(){

        Demo demo = new Demo();
        assertTrue(demo.getId()>0,"ID获取失败");
        log.info("id:{}",demo.getId());


        Example example = new Example();
        assertTrue(example.getId()>0,"ID获取失败");
        log.info("id:{}",example.getId());
    }

}
