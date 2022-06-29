package com.sankuai.inf.leaf.server;

import com.sankuai.inf.leaf.server.properties.LeafProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LeafServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeafServerApplication.class, args);
	}


	@Bean
	@ConfigurationProperties(prefix = "leaf.snowflake")
	public LeafProperties leafProperties(){
		return new LeafProperties();
	}

	@Configuration
	@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
	static class JpaDataSourceConfiguration{

		@Bean
		@ConfigurationProperties(prefix = "spring.datasource")
		public HikariConfig hikariConfig(){
			return new HikariConfig();
		}

		@Bean
		public DataSource dataSource(HikariConfig hikariConfig){
			return new HikariDataSource(hikariConfig);
		}
	}


}
