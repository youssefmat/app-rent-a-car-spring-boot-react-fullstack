package com.formation.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.mvc.entities.UserEntity;
import com.formation.mvc.entities.VoitureEntity;

public interface VoitureRepository extends JpaRepository<VoitureEntity, Long>{

	List<VoitureEntity>  findByUser(UserEntity user);
	VoitureEntity  findByVoitureId(String voitureId);
	@Query(value="SELECT * FROM `voitures` ORDER BY prix ASC",nativeQuery = true)
	List<VoitureEntity> findAllVoitures();
}
