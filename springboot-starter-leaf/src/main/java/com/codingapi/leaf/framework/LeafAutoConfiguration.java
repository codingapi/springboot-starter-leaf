package com.codingapi.leaf.framework;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AutoConfigurationImportSelector.class)
public @interface LeafAutoConfiguration {

    String[] scanBasePackages() default {};

}
