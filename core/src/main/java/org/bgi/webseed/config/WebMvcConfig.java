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
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"org.bgi.webseed.**.api.**", "org.bgi.webseed.ctrl.**"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Log logger = LogFactory.getLog(WebMvcConfig.class);
	
	private static final boolean DEV = true;
	
	public WebMvcConfig(){
		super();
		logger.info("Creating WebMvcConfig");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(DEV){
			registry.addResourceHandler("/css/**").addResourceLocations("file:/Users/guillon/dev/webseed/core/src/main/javascript/").setCachePeriod(31556926);
	        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	        registry.addResourceHandler("/js/**").addResourceLocations("file:/Users/guillon/dev/webseed/core/src/main/javascript/").setCachePeriod(31556926);
		}
		else {
			registry.addResourceHandler("/css/**").addResourceLocations("classpath:/").setCachePeriod(31556926);
	        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/").setCachePeriod(31556926);
		}
        
    }
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
    public ViewResolver viewResolver() {
		TemplateResolver templateResolver = null;
		if(DEV){
			templateResolver = new FileTemplateResolver();
			templateResolver.setSuffix(".html");
			templateResolver.setPrefix("/Users/guillon/dev/webseed/core/src/main/html/views/");
			templateResolver.setTemplateMode("XHTML");
			templateResolver.setCacheable(false);
		}
		else {
			templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setTemplateMode("XHTML");
			templateResolver.setPrefix("views/");
			templateResolver.setSuffix(".html");
		}
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
 
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        viewResolver.setCache(false);
        return viewResolver;
    }
}
