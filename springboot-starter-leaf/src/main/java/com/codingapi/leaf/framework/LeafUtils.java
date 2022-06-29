package com.codingapi.leaf.framework;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lorne
 * @since 1.0.0
 */
@Slf4j
public class LeafUtils {

    private LeafClient leafClient;

    private LeafUtils(){
    }

    private static LeafUtils instance;

    public static LeafUtils getInstance() {
        if(instance==null){
            synchronized (LeafUtils.class){
                if(instance==null){
                    instance = new LeafUtils();
                }
            }
        }
        return instance;
    }

    protected void setLeafClient(LeafClient leafClient){
        this.leafClient = leafClient;
    }

    public long segmentGetId(Class<?> clazz){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.segmentGetId(clazz.getName());
    }

    public long snowflakeGetId(Class<?> clazz){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.snowflakeGetId(clazz.getName());
    }

    public long segmentGetId(String key){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.segmentGetId(key);
    }

    public long snowflakeGetId(String key){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.snowflakeGetId(key);
    }

    public boolean push(String key,int step,int maxId){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.segmentPush(key,step,maxId);
    }
}
