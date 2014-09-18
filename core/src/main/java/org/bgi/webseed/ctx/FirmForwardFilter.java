package org.bgi.webseed.ctx;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bgi.webseed.firms.Firm;
import org.springframework.http.HttpStatus;

public class FirmForwardFilter extends AbstractFirmFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Firm firm = (Firm)request.getAttribute(FIRM_REQUEST_ATTR);
		if(firm == null){
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Firm attribute not found in request");
			return;
		}
		String servletPath = request.getServletPath();
		String newPath = FirmDetectionUtility.removeFirmFromPath(servletPath, firm.getWebContext());
		request.getRequestDispatcher(newPath).forward(request, response);
	}

}
