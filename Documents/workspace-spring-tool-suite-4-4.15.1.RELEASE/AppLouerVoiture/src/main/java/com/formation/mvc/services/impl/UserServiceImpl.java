package com.formation.mvc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formation.mvc.entities.UserEntity;
import com.formation.mvc.repository.UserRepository;
import com.formation.mvc.services.UserService;
import com.formation.mvc.shared.Utils;
import com.formation.mvc.shared.dto.UserDto;


@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Utils utils;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncreptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));
		UserEntity  userEntityCreated = userRepository.save(userEntity);
		UserDto userDtoCrated = modelMapper.map(userEntityCreated, UserDto.class);
		return userDtoCrated;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity =userRepository.findByEmail(email);
		if(userEntity == null) throw new RuntimeException("cette user n'existe pas !!");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserById(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null)  throw new RuntimeException("cette utilisateur n'existe pas !!");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if(userEntity == null) throw new RuntimeException("cette utilisateur n'existe pas !!");
		userEntity.setFristName(userDto.getFristName());
		userEntity.setListeName(userDto.getListeName());
		userEntity.setEmail(userDto.getEmail());
		UserEntity userEntityUpdated =userRepository.save(userEntity);
		UserDto userDtoUpdated = new UserDto();
		BeanUtils.copyProperties(userEntityUpdated, userDtoUpdated);
		return userDtoUpdated;
	}

	@Override
	public void deleteUser(String userid) {
		UserEntity userEntity = userRepository.findByUserId(userid);
		if(userEntity == null) throw new RuntimeException("cette utilisateur n'existe pas !!");
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getAllUsers(int page, int limite) {
		if(page >0) page -=1 ;
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limite);
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> usersEntity = new ArrayList<UserEntity>();
		usersEntity = usersPage.getContent();
		if(usersEntity == null) throw new RuntimeException("liste des utilistaeur est vide !!");
		for(UserEntity users : usersEntity) {
			
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(users, userDto);
			usersDto.add(userDto);
		}
		return usersDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncreptedPassword(), new ArrayList<>());
	}

}
