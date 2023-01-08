package com.formation.mvc.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.formation.mvc.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	   UserDto createUser(UserDto userDto);
	   UserDto getUserByEmail(String email);
	   UserDto getUserById(String userId);
	   UserDto updateUser(String id, UserDto userDto);
	   void deleteUser(String userid);
	   List<UserDto> getAllUsers(int page , int limite);

}
