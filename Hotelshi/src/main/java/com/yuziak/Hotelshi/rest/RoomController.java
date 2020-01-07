package com.yuziak.Hotelshi.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuziak.Hotelshi.entity.Climat;
import com.yuziak.Hotelshi.entity.Room;
import com.yuziak.Hotelshi.entity.User;
import com.yuziak.Hotelshi.repository.ClimRepo;
import com.yuziak.Hotelshi.repository.RoomRepo;
import com.yuziak.Hotelshi.repository.UserRepo;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	private RoomRepo roomRepo;
	@Autowired
	private ClimRepo climRepo;
	@Autowired
	private UserRepo userRepo;
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> getRoom(@PathVariable("id") Integer roomid) {
		if (roomid == null) {
			return new ResponseEntity<Room>(HttpStatus.BAD_REQUEST);
		}
		Room room = this.roomRepo.findByid(roomid);

		if (room == null) {
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> getRoomByUser(@PathVariable("id") Integer userid) {
		if (userid == null) {
			return new ResponseEntity<Room>(HttpStatus.BAD_REQUEST);
		}
		Integer roomid = this.userRepo.findByid(userid).getRoom().getId();
		Room room = this.roomRepo.findByid(roomid);
		
		if (room == null) {
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Room>> getRoom() {
		List<Room> rooms = (List<Room>) roomRepo.findAll();
		if (rooms == null) {
			return new ResponseEntity<List<Room>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> saveRoom(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		Room room = new Room();
		room.setCom((String) json.get("comm"));
		this.roomRepo.save(room);
		
		Climat climat=new Climat();
		climat.setSethumidity((Integer) json.get("humb"));
		climat.setSettemp((Integer) json.get("temp"));
		climat.setTemp(0);
		climat.setHumidity(0);
		climat.setRoom(this.roomRepo.findByid(room.getId()));
		this.climRepo.save(climat);
		
		return new ResponseEntity<Room>(room, headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> updateRoom(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		Room room = this.roomRepo.findByid((Integer) json.get("id"));
		room.setId((Integer) json.get("id"));
		room.setCom((String) json.get("comm"));
		
		Climat climat = this.climRepo.findByid(room.getClimat().getId());
		climat.setSethumidity((Integer) json.get("humb"));
		climat.setSettemp((Integer) json.get("temp"));
		if (room == null||climat==null) {
			return new ResponseEntity<Room>(HttpStatus.BAD_REQUEST);
		}
		this.roomRepo.save(room);
		this.climRepo.save(climat);
		return new ResponseEntity<Room>(room, headers, HttpStatus.OK);
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> dellRoom(@PathVariable("id") Integer roomid) {
		HttpHeaders headers = new HttpHeaders();
		Room room = this.roomRepo.findByid(roomid);
		this.roomRepo.delete(room);
		
		return new ResponseEntity<Room>(room, headers, HttpStatus.OK);
		
		
	}
}
