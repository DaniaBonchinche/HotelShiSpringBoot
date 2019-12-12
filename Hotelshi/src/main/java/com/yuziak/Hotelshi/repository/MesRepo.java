package com.yuziak.Hotelshi.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.yuziak.Hotelshi.entity.Messages;

public interface MesRepo extends PagingAndSortingRepository<Messages, Integer>  {
	
}
