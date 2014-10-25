package org.bgi.webseed.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.bgi.webseed.ctx.AbstractFirmFilter;
import org.bgi.webseed.firms.Firm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
	
	private static final String CURRENT_FIRM_MODEL_ATTR = "CURRENT_FIRM";
	
	@ModelAttribute(CURRENT_FIRM_MODEL_ATTR)
    public Firm getCurrentFirm(HttpServletRequest request) 
    {
        return (Firm) request.getAttribute(AbstractFirmFilter.FIRM_REQUEST_ATTR);
    }

	@RequestMapping
	public String index(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model, @ModelAttribute(CURRENT_FIRM_MODEL_ATTR) Firm firm){
		Assert.notNull(firm, "Current firm not set");
		return "login";
	}
	
	@RequestMapping("partials/{partial}.html")
	public String partials(@PathVariable String partial){
		return "partials/" + partial;
	}
	
	@RequestMapping("partials/admin/{partial}.html")
	public String adminPartials(@PathVariable String partial){
		return "partials/admin/" + partial;
	}
	
}
