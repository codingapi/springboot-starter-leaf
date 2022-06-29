package com.example.demo.domain;

import com.codingapi.leaf.framework.LeafIdGenerate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lorne
 * @since 1.0.0
 */
@Setter
@Getter
public class Example  implements LeafIdGenerate {

    private long id;

    private String name;

    public Example() {
        id = generateId();
    }

}
