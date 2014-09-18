package org.bgi.webseed.ctx;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.firms.Firm;
import org.springframework.http.HttpStatus;

public class FirmDetectionFilter extends AbstractFirmFilter {
	
	private static final Log log = LogFactory.getLog(FirmDetectionFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String requestedPath = request.getServletPath();
		log.info("Handling request on " + requestedPath);
		if("/login".equals(requestedPath)){
			filterChain.doFilter(request, response);
			return;
		}
		Firm firm = null;
		Object objectInRequest = request.getAttribute(FIRM_REQUEST_ATTR);
		log.info("ObjectInRequest = " + objectInRequest);
		if(objectInRequest != null && objectInRequest instanceof Firm){
			firm = (Firm)objectInRequest;
		}
		if(firm == null){
			String potentialFirm = FirmDetectionUtility.extractFirmNameFromPath(requestedPath);
			firm = this.findFirmByPath(potentialFirm, request);
			if(firm == null){
				log.error("No firm found for potential firm name " + potentialFirm);
				response.sendError(HttpStatus.FORBIDDEN.value(), "Firm not found");
				return;
			} 
		}
		
		MutableAppContext ctx = new MutableAppContext();
		ctx.setFirm(firm);
		AppContext.setCurrentAppContext(ctx);
		request.setAttribute(FIRM_REQUEST_ATTR, firm);
		filterChain.doFilter(request, response);
		AppContext.clearCurrentAppContext();
	}
	
}
