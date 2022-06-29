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

    /**
     * leaf server 地址
     */
    private String leafUrl;


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
