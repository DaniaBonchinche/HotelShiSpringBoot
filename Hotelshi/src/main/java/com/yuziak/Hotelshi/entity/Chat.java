package com.yuziak.Hotelshi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "chats")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name ="viewed")
	private boolean viewed;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "users_id", unique = true, nullable = false, updatable = false)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chat", cascade = CascadeType.REMOVE)
	@JsonBackReference
	@JsonManagedReference
	private Set<Messages> mes;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Set<Messages> getMes() {
		return mes;
	}

	public void setMes(Set<Messages> mes) {
		this.mes = mes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
}
