package com.codingapi.leaf.framework;

public interface LeafIdGenerate {

    default long generateId(){
        return LeafUtils.getInstance().generateId(getClass());
    }
}
