package com.codingapi.leaf.framework;

import com.alibaba.fastjson.JSON;
import com.codingapi.leaf.framework.properties.LeafProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lorne
 * @since 1.0.0
 */
@Slf4j
public class LeafClient {

    private final RestTemplate restTemplate;

    private final LeafProperty leafProperty ;

    public LeafClient(LeafProperty leafProperty) {
        this.restTemplate = new RestTemplate();
        this.leafProperty = leafProperty;
    }

    public long segmentGetId(String key){
        String url = String.format("%s%s%s",leafProperty.getUrl(),"api/segment/get/",key);
        String res = restTemplate.getForObject(url,String.class);
        assert res != null;
        return Long.parseLong(res);
    }

    public long snowflakeGetId(String key){
        String url = String.format("%s%s%s",leafProperty.getUrl(),"api/snowflake/get/",key);
        String res = restTemplate.getForObject(url,String.class);
        assert res != null;
        return Long.parseLong(res);
    }

    /**
     * 数据库模式添加 数据
     * @param key   key 关键字
     * @param step  每次获取数据长度 2000
     * @param maxId 开始的最大Id 1
     * @return  执行状态
     */
    public boolean segmentPush(String key, int step, int maxId){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        String url = String.format("%s%s",leafProperty.getUrl(),"api/push");
        Map<String,Object> request = new HashMap<>();
        request.put("key",key);
        request.put("step",step);
        request.put("maxId",maxId);
        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(request), requestHeaders);
        String result = restTemplate.postForObject(url, requestEntity,String.class);
        return "success".equals(result);
    }

    public void init(){
        LeafUtils.getInstance().setLeafClient(this);
        this.initKeys();
        this.initClasses();

        log.info("leaf init finish.");
    }

    private void initKeys(){
        String[] keys =  leafProperty.getKeys();
        if(keys!=null&&keys.length>0) {
            for (String key : keys) {
                try {
                    LeafUtils.getInstance().push(key, 2000, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initClasses(){
        Class<?>[] classes =  leafProperty.getClasses();
        if(classes!=null&&classes.length>0) {
            for (Class<?> clazz : classes) {
                try {
                    LeafUtils.getInstance().push(clazz.getName(), 2000, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
