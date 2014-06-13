package org.bgi.webseed.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"org.bgi.webseed.api.**", "org.bgi.webseed.ctrl.**"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Log logger = LogFactory.getLog(WebMvcConfig.class);
	
	public WebMvcConfig(){
		super();
		logger.info("Creating WebMvcConfig");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**/*.css").addResourceLocations("classpath:/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/").setCachePeriod(31556926);
    }
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
    public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver result = new SpringResourceTemplateResolver();
		result.setPrefix("classpath:/views/");
        result.setSuffix(".html");
        result.setTemplateMode("HTML5");
        return result;
    }
	
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
		SpringTemplateEngine result = new SpringTemplateEngine();
		result.setTemplateResolver(templateResolver);
		return result;
	}
	
	@Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setOrder(1);
        return viewResolver;
    }
	
}
