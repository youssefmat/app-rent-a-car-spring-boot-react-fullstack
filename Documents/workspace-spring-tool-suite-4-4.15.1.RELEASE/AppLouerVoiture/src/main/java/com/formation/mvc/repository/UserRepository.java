package com.formation.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.mvc.entities.UserEntity;
import com.formation.mvc.shared.dto.UserDto;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	 UserEntity  findByEmail(String email);
	 UserEntity  findByUserId(String userId);

}
