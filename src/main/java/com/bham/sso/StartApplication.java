package com.bham.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.web.MustacheViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Objects;

@ComponentScan("com.bham.sso.*")
@EnableTransactionManagement
@SpringBootApplication
public class StartApplication extends WebMvcConfigurerAdapter{
   /* public static void main(String[] args) throws Exception {
        SpringApplication.run(StartApplication.class, args);
    }*/
    
    private static ApplicationContext ctx;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("classpath:/templates/");
        mustacheViewResolver.setSuffix(".html");
        mustacheViewResolver.setCharset("UTF-8");
        mustacheViewResolver.setCache(true);
        mustacheViewResolver.setCacheLimit(1024);

        registry.viewResolver(mustacheViewResolver);
    }

    public static ApplicationContext getApplicationContext() {
        if (Objects.isNull(ctx)) {
            throw new NullPointerException();
        }

        return ctx;
    }

    public static void main(String[] args) {
        ctx = SpringApplication.run(StartApplication.class, args);
    }
}

