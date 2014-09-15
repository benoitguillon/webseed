package org.bgi.webseed.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.ctx.AppContext;
import org.bgi.webseed.ctx.MutableAppContext;
import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.firms.FirmsRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class FirmFilter extends OncePerRequestFilter {

	private static final Log log = LogFactory.getLog(FirmFilter.class);
	
	public static final String FIRM_REQUEST_ATTR = "firm";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		log.info("doFilterInternal start ... ");
		
		String requestUri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		String pathTranslated = request.getPathTranslated();
		String queryString = request.getQueryString();
		String servletPath = request.getServletPath();
		log.info("Processing RequestURI=[" + requestUri + "]. "+
				"ContextPath=[" + ctxPath + "]. " +
				"PathInfo=[" + pathInfo + "]. " +
				"PathTranslated=[" + pathTranslated + "]. " +
				"QueryString=[" + queryString + "]. " +
				"ServletPath=[" + servletPath + "]");
		//filterChain.doFilter(request, response);
		int start = 0;
		if(servletPath.startsWith("/")){
			start = 1;
		}
		int end = servletPath.indexOf('/', 1);
		if(end == -1){
			end = servletPath.length();
		}
		String potentialFirm = servletPath.substring(start, end);
		log.info("Potential firm found " + potentialFirm);
		
		Firm firm = getFirm(potentialFirm, request);
		if(firm == null){
			log.error("No firm found for potential firm name " + potentialFirm);
			response.sendError(403, "Firm not found");
		}
		else {
			log.info("Found firm " + firm.getName() + " for potential name " + potentialFirm);
			MutableAppContext ctx = new MutableAppContext();
			ctx.setFirm(firm);
			AppContext.setCurrentAppContext(ctx);
			request.setAttribute(FIRM_REQUEST_ATTR, firm);
			String path = removeFirmFromPath(servletPath, firm.getWebContext());
			request.getRequestDispatcher(path).forward(request, response);
			AppContext.clearCurrentAppContext();
		}
		log.info("doFilterInternal end");
	}
	
	private String removeFirmFromPath(String path, String firmWebContext){
		int start = 0;
		boolean mustStartWithSlash = false;
		if(path.startsWith("/")){
			start = 1;
			mustStartWithSlash = true;
		}
		int len = firmWebContext.length();
		String result = path.substring(start + len);
		if(!result.startsWith("/") && mustStartWithSlash){
			result = "/" + result;
		}
		return result;
	}
	
	private Firm getFirm(String potentialFirm, HttpServletRequest request){
		FirmsRepository service = getFirmDataService(request);
		Map<String, Firm> firms = buildFirmIndex(service);
		return firms.get(potentialFirm);
	}
	
	private Map<String, Firm> buildFirmIndex(FirmsRepository firmDataService){
		Iterable<Firm> firms = firmDataService.findAll();
		Map<String, Firm> result = new HashMap<String, Firm>();
		for(Firm f: firms){
			result.put(f.getWebContext(), f);
		}
		return result;
	}
	
	private FirmsRepository getFirmDataService(HttpServletRequest request){
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object o = ctx.getBean("firmsRepository");
		System.out.println(o.getClass());
		FirmsRepository firmDataService = (FirmsRepository)ctx.getBean("firmsRepository");
		return firmDataService;
	}
}
