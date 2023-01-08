package com.formation.mvc.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formation.mvc.requests.UserRequest;
import com.formation.mvc.responses.UserResponse;
import com.formation.mvc.services.UserService;
import com.formation.mvc.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	// créer utilisateur 
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE},
			     produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto userCreated = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(userCreated, UserResponse.class);
		
		
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
		
	}
	//recupérer les listes d'utulisateur avec pagination
	@GetMapping(produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam( value="page", defaultValue ="1") int page, @RequestParam(value="limite", defaultValue ="3" ) int limite){
		List<UserDto> usersDto =userService.getAllUsers(page, limite);
		Type listType = new TypeToken<List<UserResponse>>() {}.getType();
		List<UserResponse> usersResponse = new ModelMapper().map(usersDto,listType );

		return new ResponseEntity<List<UserResponse>>( usersResponse,HttpStatus.OK);
		
	}
	// recupépere un utilsateur par id 
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> getUserParId(@PathVariable String id){
		UserDto userDto =userService.getUserById(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	//recupérer un utilisateur par email
	@GetMapping(path = "/{email}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> getUserParEmail(@PathVariable String email){
		 UserDto userDto = userService.getUserByEmail(email);
		 UserResponse userResponse = new UserResponse();
		 BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	//supprimer un utilisateur par id
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Object> deletUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//modiffier un utilisateur
	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> upadetUser(@PathVariable String id, @RequestBody UserRequest userRequest ){
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto userUpdated =userService.updateUser(id, userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userUpdated, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
	}
	
	

}
