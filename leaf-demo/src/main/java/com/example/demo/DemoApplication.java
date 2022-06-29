package com.example.demo;

import com.codingapi.leaf.framework.LeafScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lorne
 * @since 1.0.0
 */
@LeafScanner
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
