package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yuziak.Hotelshi.entity.User;

public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
	User findByusername(String name);
	User findByid(Integer id);
}
