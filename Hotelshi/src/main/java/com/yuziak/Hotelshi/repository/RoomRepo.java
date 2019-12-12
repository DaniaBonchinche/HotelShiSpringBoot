package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.CrudRepository;

import com.yuziak.Hotelshi.entity.Room;

public interface RoomRepo extends CrudRepository<Room, Integer>  {
	Room findByid(int id);
}
