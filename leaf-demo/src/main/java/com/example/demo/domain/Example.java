package com.example.demo.domain;

import com.codingapi.leaf.framework.LeafUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lorne
 * @since 1.0.0
 */
@Setter
@Getter
public class Example {

    private long id;

    private String name;


    public Example() {
        this.generatedId();
    }


    private void generatedId(){
        this.id = LeafUtils.getInstance().generateId(Example.class);
    }
}
