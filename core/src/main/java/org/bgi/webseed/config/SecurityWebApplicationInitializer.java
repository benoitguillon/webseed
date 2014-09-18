package org.bgi.webseed.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.bgi.webseed.ctx.FirmDetectionFilter;
import org.bgi.webseed.ctx.FirmForwardFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic firmDetectionFilter = servletContext.addFilter("firmDetectionFilter", new FirmDetectionFilter());
		firmDetectionFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		this.appendFilters(servletContext, new FirmForwardFilter());
	}
	
	
}
