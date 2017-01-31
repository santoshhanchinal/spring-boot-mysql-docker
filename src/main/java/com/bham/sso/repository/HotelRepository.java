package com.bham.sso.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bham.sso.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
	@Override
	List<Hotel> findAll();
}
