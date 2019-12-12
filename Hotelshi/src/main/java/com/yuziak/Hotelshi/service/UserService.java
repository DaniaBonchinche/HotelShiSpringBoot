package com.yuziak.Hotelshi.service;

import java.util.List;

import com.yuziak.Hotelshi.entity.User;

public interface UserService {
	User register(User user);

	List<User> getAll();

	User findByUsername(String username);

	User findById(Integer id);

	void delete(Integer id);
}
