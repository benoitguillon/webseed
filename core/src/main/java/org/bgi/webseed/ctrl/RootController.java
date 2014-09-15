package org.bgi.webseed.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping
	public String index(){
		return "index";
	}
	
	@RequestMapping("partials/{partial}.html")
	public String partials(@PathVariable String partial){
		return partial;
	}
	
	@RequestMapping("partials/admin/{partial}.html")
	public String adminPartials(@PathVariable String partial){
		return "admin/" + partial;
	}
	
}
