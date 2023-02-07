package org.zerock.b01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/assets/css/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/assets/img/");
//        registry.addResourceHandler("/fonts/**")
//                .addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/vendor/**")
                .addResourceLocations("/static/assets/vendor/**");

    }
}
