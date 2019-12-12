package com.yuziak.Hotelshi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="climats")
public class Climat {
	
	public Climat() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@OneToOne(optional = false,fetch =FetchType.EAGER )
	@JsonBackReference
	@JoinColumn(name="rooms_id", unique = true, nullable = false, updatable = true)
	private Room room;

	@Column(name="temp")
	private int temp;
	
	@Column(name="humidity")
	private int humidity;
	
	@Column(name="settemp")
	private int settemp;
	
	@Column(name="sethumidity")
	private int sethumidity;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getSettemp() {
		return settemp;
	}

	public void setSettemp(int settemp) {
		this.settemp = settemp;
	}

	public int getSethumidity() {
		return sethumidity;
	}

	public void setSethumidity(int sethumidity) {
		this.sethumidity = sethumidity;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
