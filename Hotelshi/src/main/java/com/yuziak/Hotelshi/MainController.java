package com.yuziak.Hotelshi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuziak.Hotelshi.entity.Room;
import com.yuziak.Hotelshi.repository.RoomRepo;

import net.minidev.json.JSONObject;

@Controller
public class MainController {
	@Autowired
    private RoomRepo roomRepo;
	
	
	@GetMapping()
	public String home() {
		return "index";
	}
	
	
	 @PostMapping
	    public String add(@RequestParam String com, Map<String, Object> model) {
	        Room room = new Room(com);

	        roomRepo.save(room);

	        Iterable<Room> rooms = roomRepo.findAll();

	        model.put("room", rooms);

	        return "home";
	    }

}
