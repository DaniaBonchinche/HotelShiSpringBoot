package com.yuziak.Hotelshi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuziak.Hotelshi.entity.User;
import com.yuziak.Hotelshi.repository.UserRepo;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserRepo UserRepo;
	
	
	@CrossOrigin (origins = "http://localhost:3000")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getRoom(@PathVariable("id") Integer userid) {
		if (userid == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		User user = this.UserRepo.findByid(userid);

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
