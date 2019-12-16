package com.yuziak.Hotelshi.rest;

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
import com.yuziak.Hotelshi.repository.ClimRepo;
import com.yuziak.Hotelshi.repository.RoomRepo;

import net.minidev.json.JSONObject;;

@RestController
@RequestMapping("api/climat")
public class ClimatController {
	@Autowired
	private ClimRepo climRepo;

	@Autowired
	private RoomRepo RoomRepo;
	
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Climat> saveClimat(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		Climat climat = new Climat();
		climat.setHumidity((Integer) json.get("humidity"));
		climat.setTemp((Integer) json.get("temp"));
		climat.setSethumidity((Integer) json.get("sethumidity"));
		climat.setSettemp((Integer) json.get("settemp"));
		Integer roomid = (Integer) json.get("rooms_id");
		Room room = this.RoomRepo.findByid(roomid);
		climat.setRoom(room);

		this.climRepo.save(climat);
		return new ResponseEntity<Climat>(climat, headers, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Climat> updateClimat(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		Climat climat = new Climat();
		climat.setId((Integer) json.get("id"));
		climat.setHumidity((Integer) json.get("humidity"));
		climat.setTemp((Integer) json.get("temp"));
		climat.setSethumidity((Integer) json.get("sethumidity"));
		climat.setSettemp((Integer) json.get("settemp"));
		Integer roomid = (Integer) json.get("rooms_id");
		Room room = this.RoomRepo.findByid(roomid);
		climat.setRoom(room);

		this.climRepo.save(climat);
		return new ResponseEntity<Climat>(climat, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Climat> getClimat(@PathVariable("id") int climid) {
		if (climid == 0) {
			return new ResponseEntity<Climat>(HttpStatus.BAD_REQUEST);
		}
		Climat climat = this.climRepo.findByid(climid);

		if (climat == null) {
			return new ResponseEntity<Climat>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Climat>(climat, HttpStatus.OK);
	}
}
