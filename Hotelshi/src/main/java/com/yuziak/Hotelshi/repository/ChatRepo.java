package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yuziak.Hotelshi.entity.Chat;
import com.yuziak.Hotelshi.entity.Climat;

public interface ChatRepo extends PagingAndSortingRepository<Chat, Integer> {
	Chat findByid(int id);
	Chat findByuser_id(int id);
}
