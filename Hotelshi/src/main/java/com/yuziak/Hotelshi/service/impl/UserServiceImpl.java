package com.yuziak.Hotelshi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuziak.Hotelshi.entity.Role;
import com.yuziak.Hotelshi.entity.User;
import com.yuziak.Hotelshi.repository.RoleRepo;
import com.yuziak.Hotelshi.repository.UserRepo;
import com.yuziak.Hotelshi.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepository;

	@Autowired
    private  RoleRepo roleRepository;

	@Autowired
    private  BCryptPasswordEncoder passwordEncoder;
	
	
    public User register(User user) {
        Role roleUser = roleRepository.findByname("ROLE_USER");
        List<Role> userRoles = new ArrayList<Role>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);


        return registeredUser;
    }

    public List<User> getAll() {
        List<User> result = (List<User>) userRepository.findAll();
        return result;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByusername(username);
        return result;
    }

	public User findById(Integer id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }


}
