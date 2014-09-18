package org.bgi.webseed.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Log log = LogFactory.getLog(SecurityConfig.class);
	
	@Autowired
	private AuthenticationProvider customAuthenticationProvider;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		log.info("Configuring security");
		http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .httpBasic();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(customAuthenticationProvider);
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
	
	
}
