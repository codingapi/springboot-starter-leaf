package com.codingapi.leaf.framework;

import com.codingapi.leaf.framework.properties.LeafProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lorne
 * @since 1.0.0
 */
@Configuration
public class LeafConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "laiease.leaf-properties")
    public LeafProperty leafProperty(){
        return new LeafProperty();
    }

    @Bean(initMethod = "init")
    public LeafClient leafClient(LeafProperty leafProperty){
        return new LeafClient(leafProperty);
    }

}
