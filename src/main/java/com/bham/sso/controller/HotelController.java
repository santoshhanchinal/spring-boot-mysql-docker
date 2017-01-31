package com.bham.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hotels")
public class HotelController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) {
		return "hotels/home";
	}
	
    @RequestMapping("test")
    public ModelAndView handleRequest(Model model) {
        model.addAttribute("title", "Hello Mustache");
        return new ModelAndView("home").addAllObjects(model.asMap());
    }
	
}
