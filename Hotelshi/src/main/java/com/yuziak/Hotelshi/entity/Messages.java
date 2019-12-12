package com.yuziak.Hotelshi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "messages")
public class Messages {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreatedDate
	@Column(name = "time")
	private Date time;
	
	@Column(name="text")
	private String text;
	
	@Column(name="aut")
	private String aut;

	@ManyToOne(fetch =FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="chats_id", unique = false, nullable = false, updatable = true)
	private Chat chat;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAut() {
		return aut;
	}

	public void setAut(String aut) {
		this.aut = aut;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	

}
