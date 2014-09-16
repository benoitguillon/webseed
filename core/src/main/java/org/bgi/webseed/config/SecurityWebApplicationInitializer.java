package org.bgi.webseed.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	private static final Log log = LogFactory.getLog(SecurityWebApplicationInitializer.class);
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		log.info("Setting up firmFilter");
		FilterRegistration.Dynamic firmFilter = servletContext.addFilter("firmFilter", new FirmFilter());
		firmFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
