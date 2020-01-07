package com.yuziak.Hotelshi.rest;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuziak.Hotelshi.entity.Chat;
import com.yuziak.Hotelshi.entity.Messages;
import com.yuziak.Hotelshi.entity.Role;
import com.yuziak.Hotelshi.entity.Room;
import com.yuziak.Hotelshi.entity.User;
import com.yuziak.Hotelshi.repository.ChatRepo;
import com.yuziak.Hotelshi.repository.RoleRepo;
import com.yuziak.Hotelshi.repository.RoomRepo;
import com.yuziak.Hotelshi.repository.UserRepo;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private RoomRepo RoomRepo;
	
	@Autowired
	private ChatRepo ChatRepo;
	
	@Autowired
	private RoleRepo roleRepository;
	
	@Autowired
    private  BCryptPasswordEncoder passwordEncoder;
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Integer userid) {
		if (userid == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		User user = this.UserRepo.findByid(userid);

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
		
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<User>> getUsers() {
		
		List<User> users =(List<User>) this.UserRepo.findAll();

		if (users == null) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> saveUser(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		User user = new User();
		user.setId((Integer) json.get("id"));
		user.setName((String) json.get("name"));
		user.setUsername((String) json.get("userName"));
		user.setPassword(passwordEncoder.encode((String) json.get("pass")));
		Integer roomId = (Integer) json.get("roomid");
		Room room = this.RoomRepo.findByid(roomId);
		user.setRoom(room);
		user.setRoles(UserRepo.findByid((Integer) json.get("id")).getRoles());
		
		this.UserRepo.save(user);

		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
	
	
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		User user = new User();
		user.setName((String) json.get("name"));
		user.setUsername((String) json.get("userName"));
		user.setPassword(passwordEncoder.encode((String) json.get("pass")));
		Integer roomId = (Integer) json.get("roomid");
		Room room = this.RoomRepo.findByid(roomId);
		user.setRoom(room);
		List<Role> roles = new LinkedList<Role>();
		
		roles.add(roleRepository.findByname("ROLE_USER"));
		user.setRoles(roles);
		
		this.UserRepo.save(user);

		Chat chat = new Chat();
		
		chat.setId(user.getId());
		chat.setUser(UserRepo.findByusername((String) json.get("userName")));
		this.ChatRepo.save(chat);
		
		return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
		
		
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> dellUser(@PathVariable("id") Integer userid) {
		HttpHeaders headers = new HttpHeaders();
		User user = this.UserRepo.findByid(userid);
		this.UserRepo.delete(user);
		
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
		
		
	}
	
	
}
