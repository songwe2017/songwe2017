package com.admin.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Songwe
 * @date 2022/5/24 22:09
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    //}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
    }
}
