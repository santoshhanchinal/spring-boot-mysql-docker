package com.bham.sso.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bham.sso.entity.Hotel;
import com.bham.sso.repository.HotelRepository;

@Controller
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
    private HotelRepository repository;
	
	@RequestMapping("list")
	public String list() {
	    StringBuilder sb = new StringBuilder();
	    Iterator<Hotel> ht = this.repository.findAll().iterator();
	    while(ht.hasNext()){
	    	sb.append(ht.next());
	    }
	    return sb.toString();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Hotel get(@PathVariable("id") long id) {
		return this.repository.findOne(id);
	}
	
}
