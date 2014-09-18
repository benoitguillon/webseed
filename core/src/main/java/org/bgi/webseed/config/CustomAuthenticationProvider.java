package org.bgi.webseed.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.ctx.AppContext;
import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.users.User;
import org.bgi.webseed.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Log log = LogFactory.getLog(CustomAuthenticationProvider.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final String userName = auth.getName();
		log.info("Handling authentication request for " + userName);
		Firm firm = AppContext.getCurrentAppContext().getCurrentFirm();
		if(firm == null){
			throw new BadCredentialsException("No firm set in context, cannot perform authentication");
		}
		User user = usersRepository.findByFirmAndUserName(firm, userName);
		if(user == null){
			throw new BadCredentialsException("Invalid credentials");
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

}
