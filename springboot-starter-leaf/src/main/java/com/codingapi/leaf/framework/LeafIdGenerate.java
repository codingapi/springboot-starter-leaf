package com.codingapi.leaf.framework;

public interface LeafIdGenerate {

    default long generateId(){
        Class<?> clazz = getClass();
        return LeafUtils.getInstance().generateId(clazz);
    }
}
