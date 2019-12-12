package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yuziak.Hotelshi.entity.Role;

public interface RoleRepo extends PagingAndSortingRepository<Role, Integer>{
	 Role findByname(String name);
}
