/**
* <p>Title: IndexController.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: UOB</p>
* @author RuJia
* @date 2017年1月31日
* @version 1.0
*/
package com.bham.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bham.sso.entity.Person;
import com.bham.sso.repository.PersonRepository;

/**
* <p>Title: IndexController.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: UOB</p>
* @author RuJia
* @date 2017年1月31日
* @version 1.0
*/
@RestController
public class IndexController {
	@Autowired
	private PersonRepository repository;
	
	@RequestMapping("/test")
	public String home() {
	    //Person p = this.repository.findAll().iterator().next();
	    return "/index/index.jsp";
	}
	
	@RequestMapping("/")
	public String home1() {
	    Person p = this.repository.findAll().iterator().next();
	    return "Hello " + p.getName() + "!!!!!!!!!!!!!!!!";
	}
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
	     ModelAndView mv = new ModelAndView("/index/index");
	     return mv;
	}
	 @RequestMapping(value ="/register",method =RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
	     ModelAndView mv = new ModelAndView("/register/register","command","success");
	     return mv;
	}
}
