package com.mruruc.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.*;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mruruc")
@PropertySources(value = {
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:log-config.properties")
})
public class WebConfig implements WebApplicationInitializer, WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // login view controller
        registry.addViewController("/login").setViewName("user/login");
        // register view controller
        registry.addViewController("/registration").setViewName("user/register");
        // address registration view controller
        registry.addViewController("/address").setViewName("user/address-page");

        registry.addViewController("/new-product").setViewName("product/new-product");
        registry.addViewController("/products-price-range").setViewName("product/products-price-range");
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springContainer = new AnnotationConfigWebApplicationContext();
        springContainer.register(WebConfig.class);
        ServletRegistration.Dynamic frontController =
                servletContext.addServlet("frontController", new DispatcherServlet(springContainer));
        frontController.addMapping("/");
        frontController.setLoadOnStartup(1);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
