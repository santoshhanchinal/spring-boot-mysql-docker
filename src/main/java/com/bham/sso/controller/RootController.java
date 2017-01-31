package com.bham.sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
* <p>Title: RootController.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: UOB</p>
* @author RuJia
* @date 2017年1月31日
* @version 1.0
 */
@RestController
public class RootController {
	@RequestMapping("/")
	public String onRootAccess() {
		  return "Hello Docker World."
	                + "<br />Welcome to <a href='http://www.ehcoo.com'>ehcoo.com</a></li>";
	}
}
