package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.CrudRepository;

import com.yuziak.Hotelshi.entity.Climat;

public interface ClimRepo extends CrudRepository<Climat, Integer> {
	Climat findByid(int id);
}
