[![Build Status](https://app.travis-ci.com/codingapi/springboot-starter-leaf.svg?branch=main)](https://app.travis-ci.com/codingapi/springboot-starter-leaf)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/codingapi/springboot-starter-leaf/blob/main/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.codingapi.leaf/springboot-starter-leaf.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.codingapi.leaf%22%20AND%20a:%22springboot-starter-leaf%22)

# springboot-starter-leaf

```xml
<dependency>
    <groupId>com.codingapi.leaf</groupId>
    <artifactId>springboot-starter-leaf</artifactId>
    <version>${last.version}</version>
</dependency>
```

## Tutorials

### run leaf server

* setting application.properties
```
# enable segemnt,setting jdbc  
leaf.segment.enable=true
spring.datasource.jdbc-url=jdbc:h2:./leaf.db
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update

# enable snowflake,setting Zookeeper  
leaf.snowflake.enable=false
#leaf.snowflake.zkAddress=
#leaf.snowflake.port=
```

### run leaf demo  
* setting application.properties

```
# setting leaf server url
codingapi.leaf-properties.leaf-url=http://127.0.0.1:8080/

```

* add Application @LeafAutoConfiguration
```

@LeafAutoConfiguration
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}

```
LeafAutoConfiguration Specify `LeafIdGenerate` scanBasePackages
```
@LeafAutoConfiguration(scanBasePackages = {"com.example.demo"})

```

* implements LeafIdGenerate
```
import com.codingapi.leaf.framework.LeafIdGenerate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lorne
 * @since 1.0.0
 */
@Setter
@Getter
public class Demo implements LeafIdGenerate {

    private long id;

    private String name;

    public Demo() {
        id = this.generateLongId();
    }

}

```

* use generateLongId();
```
id = this.generateLongId();
LeafUtils.getInstance().generateId(Demo.class);
```

## Link 
https://github.com/Meituan-Dianping/Leaf 
