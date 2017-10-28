package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.security.realm.MongoRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@ComponentScan(basePackages = "com.sample.rest.demo.springbootrest")
@PropertySources({
    @PropertySource("classpath:application.properties")
})
public class SpringBootRestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(SpringBootRestApplication.class);
    }


    @Bean
    public Realm realm() {
        return new MongoRealm();
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // use permissive to NOT require authentication, our controller Annotations will decide that
        chainDefinition.addPathDefinition("/register/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/fonts/**", "anon");
        chainDefinition.addPathDefinition("/images/**", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

}