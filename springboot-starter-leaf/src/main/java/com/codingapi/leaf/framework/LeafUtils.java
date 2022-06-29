package com.codingapi.leaf.framework;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author lorne
 * @since 1.0.0
 */
@Slf4j
public class LeafUtils {

    private LeafClient leafClient;
    private String type;
    @Setter
    private Set<Class<? extends LeafIdGenerate>> classes;

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
        this.type = leafClient.type();
        this.initClass();
    }

    boolean isSegment(){
        return "SEGMENT".equalsIgnoreCase(type);
    }

    boolean isSnowflake(){
        return "SNOWFLAKE".equalsIgnoreCase(type);
    }

    public long generateId(Class<?> clazz){
        if(isSegment()){
            return segmentGetId(clazz);
        }
        if(isSnowflake()){
            return snowflakeGetId(clazz);
        }
        throw new RuntimeException("springboot init finish then to running.");
    }

    private long segmentGetId(Class<?> clazz){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.segmentGetId(clazz.getName());
    }

    private long snowflakeGetId(Class<?> clazz){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.snowflakeGetId(clazz.getName());
    }


     boolean push(String key,int step,int maxId){
        if(leafClient==null){
            throw new RuntimeException("springboot init finish then to running.");
        }
        return leafClient.segmentPush(key,step,maxId);
    }


    private void initClass(){
        if(LeafUtils.getInstance().isSegment()) {
            if(classes!=null&&classes.size()>0) {
                for (Class<?> clazz : classes) {
                    try {
                        LeafUtils.getInstance().push(clazz.getName(), 2000, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        log.info("leaf init finish.");
    }

}
