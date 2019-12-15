package com.yuziak.Hotelshi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="rooms")
public class Room {
	public Room() {
		// TODO Auto-generated constructor stub
	}
	public Room(String com) {
		this.com=com;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name="comment")
	private String com;
	
	@OneToOne(optional = false, mappedBy="room",fetch =FetchType.LAZY)
	@JsonManagedReference
	private User user;
	
	@OneToOne(optional = false, mappedBy = "room",fetch = FetchType.LAZY)
	@JsonManagedReference
	private Climat climat;

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
