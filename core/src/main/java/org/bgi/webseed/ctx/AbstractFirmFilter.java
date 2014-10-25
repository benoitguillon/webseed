package org.bgi.webseed.ctx;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.firms.FirmsRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class AbstractFirmFilter extends OncePerRequestFilter {
	
	private static final Log log = LogFactory.getLog(AbstractFirmFilter.class);
	
	public static final String FIRM_REQUEST_ATTR = "firm";
	
	public static final String FIRM_ID_PARAM_NAME = "firmId";
	
	protected Firm findFirmInRequest(HttpServletRequest request){
		Object objectInRequest = request.getAttribute(FIRM_REQUEST_ATTR);
		if(objectInRequest != null && objectInRequest instanceof Firm){
			log.info("Firm object in request attributes = " + objectInRequest);
			return (Firm)objectInRequest;
		}
		String firmIdParamValue = request.getParameter(FIRM_ID_PARAM_NAME);
		if(!StringUtils.isEmpty(firmIdParamValue)){
			log.info("Found firm id in HTTP parameters " + firmIdParamValue);
			long firmId = Long.valueOf(firmIdParamValue);
			return getFirmRepository(request).findOne(firmId);
		}
		log.info("Nothing found regarding the current firm in request");
		return null;
	}
	
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
