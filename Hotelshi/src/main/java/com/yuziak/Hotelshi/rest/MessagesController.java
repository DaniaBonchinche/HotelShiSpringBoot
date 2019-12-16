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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuziak.Hotelshi.entity.Chat;
import com.yuziak.Hotelshi.entity.Messages;
import com.yuziak.Hotelshi.repository.ChatRepo;
import com.yuziak.Hotelshi.repository.MesRepo;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api/mes")
public class MessagesController {
	@Autowired
	MesRepo mesRepo;

	@Autowired
	ChatRepo chatRepo;
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Messages> saveMessage(@RequestBody @Valid JSONObject json) {
		HttpHeaders headers = new HttpHeaders();
		Messages messages = new Messages();
		messages.setText((String) json.get("text"));
		messages.setAut((String) json.get("aut"));
		messages.setTime(new Date());
		Integer chatId = (Integer) json.get("chats_id");
		Chat chat = this.chatRepo.findByid(chatId);
		messages.setChat(chat);

		this.mesRepo.save(messages);
		return new ResponseEntity<Messages>(messages, headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin (origins = {"http://localhost:3000","http://109.86.204.249:3000"})
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Messages>> getMessages(@PathVariable("id") Integer userid) {
		if (userid == null) {
			return new ResponseEntity<List<Messages>>(HttpStatus.BAD_REQUEST);
		}
		List<Messages> messages = (List<Messages>) this.mesRepo.findAll();
		List<Messages> messageres =new LinkedList<Messages>();
		
		for (Messages m : messages) {
			if (m.getChat().getUser().getId() == userid) {
				messageres.add(m);
			}
		}
		return new ResponseEntity<List<Messages>>(messageres, HttpStatus.OK);
	}

}
