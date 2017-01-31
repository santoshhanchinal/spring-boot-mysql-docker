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

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonRepository repository;
	
	@RequestMapping("/list")
	public String list() {
	    Person p = this.repository.findAll().iterator().next();
	    StringBuilder sb = new StringBuilder();
	    Iterator<Person> ip = this.repository.findAll().iterator();
	    while(ip.hasNext()){
	    	sb.append(ip.next());
	    }
	    return sb.toString();
	}
}
