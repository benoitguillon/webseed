package org.bgi.webseed.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic firmFilter = servletContext.addFilter("firmFilter", new FirmFilter());
		firmFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
