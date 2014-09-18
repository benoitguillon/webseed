package org.bgi.webseed.ctx;

import javax.servlet.http.HttpServletRequest;

import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.firms.FirmsRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class AbstractFirmFilter extends OncePerRequestFilter {
	
	public static final String FIRM_REQUEST_ATTR = "firm";
	
	protected Firm findFirmByPath(String path, HttpServletRequest request){
		FirmsRepository firmRepo = getFirmRepository(request);
		return firmRepo.findByWebContext(path);
	}
	
	
	protected FirmsRepository getFirmRepository(HttpServletRequest request){
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		FirmsRepository firmRepo = ctx.getBean(FirmsRepository.class);
		return firmRepo;
	}

}
