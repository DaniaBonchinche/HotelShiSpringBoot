package com.yuziak.Hotelshi.repository;

import org.springframework.data.repository.CrudRepository;

import com.yuziak.Hotelshi.entity.Room;
import com.yuziak.Hotelshi.entity.User;

public interface RoomRepo extends CrudRepository<Room, Integer>  {
	Room findByid(int id);
	Room findByuser(User user);
}
