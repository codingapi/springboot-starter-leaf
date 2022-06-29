package com.codingapi.leaf.framework.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lorne
 * @since 1.0.0
 */
@Setter
@Getter
public class LeafProperty {

    public enum Type{
        /**
         * 数据库自增模式
         */
        SEGMENT,
        /**
         * 雪花算法
         */
        SNOWFLAKE
    }

    /**
     * leaf server 地址
     */
    private String leafUrl;

    /**
     * 请求模式
     */
    private Type type = Type.SEGMENT;

    /**
     * 注册leaf key Class 格式
     */
    private Class<?>[] classes;

    /**
     * 注册leaf key str 格式
     */
    private String[] keys;

    public String getUrl(){
        if(leafUrl==null){
            throw new RuntimeException("leafUrl mast not null.");
        }
        String url = leafUrl.trim();
        if(url.endsWith("/")){
            return url;
        }else{
            return url+"/";
        }
    }
}
