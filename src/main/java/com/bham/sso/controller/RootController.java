package com.bham.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The only purpose of this root controller is to redirect the user on root context 
 * access to the right home page
 * 
 * @author Biju Kunjummen
 *
 */
@Controller
public class RootController {
	@RequestMapping("/")
	public String onRootAccess() {
		  return "Hello Docker World."
	                + "<br />Welcome to <a href='http://www.ehcoo.com'>ehcoo.com</a></li>";
	}
}
