package com.starter;

import com.lib.HelloService;
import com.lib.TypicalHelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 24: Note that "hello-starter" has its own configuration class,
//          in which "TypicalHelloService" bean is configured.
@Configuration

// ---------------------------------------------

// 33: Add @ConditionalOnClass(HelloService.class) to the class
// - This will make sure this auto-configuration class is used only
//   when the "HelloService.class" is in the classpath
@ConditionalOnClass(HelloService.class)
public class HelloAutoConfig {

    // 34: Add @ConditionalOnMissingBean(HelloService.class) to the method
    // - This will make sure this "HelloService" bean will be auto-configured
    //   only when there is no "HelloService" bean already in the application
    //   context
    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    HelloService helloService() {
        return new TypicalHelloService();
    }

}

