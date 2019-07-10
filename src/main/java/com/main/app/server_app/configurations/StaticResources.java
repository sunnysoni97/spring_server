package com.main.app.server_app.configurations;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.main.app.server_app.common.consoleLogger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResources extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path path = Paths.get("images/");
        String pathString = "file:"+path.toAbsolutePath().toString()+"/";
        registry.addResourceHandler("/images/**").addResourceLocations(pathString);
        consoleLogger.info("Added Resource Handler - images");
    }
}