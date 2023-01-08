package com.formation.mvc.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.mvc.entities.UserEntity;
import com.formation.mvc.entities.VoitureEntity;
import com.formation.mvc.repository.UserRepository;
import com.formation.mvc.repository.VoitureRepository;
import com.formation.mvc.services.VoitureService;
import com.formation.mvc.shared.Utils;
import com.formation.mvc.shared.dto.UserDto;
import com.formation.mvc.shared.dto.VoitureDto;

@Service
public class VoitureServiceImpl implements VoitureService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	VoitureRepository voitureRepository;
	

	@Override
	public VoitureDto createVoiture(VoitureDto voitureDto, String email) {
		UserEntity currentUser =userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		voitureDto.setVoitureId(utils.generateStringId(32));
		voitureDto.setUser(userDto);
		VoitureEntity voitureEntity = modelMapper.map(voitureDto, VoitureEntity.class);
		VoitureEntity newVoiture =voitureRepository.save(voitureEntity);
		VoitureDto newVoitureDto =modelMapper.map(newVoiture, VoitureDto.class);
		return newVoitureDto;
	}

	@Override
	public VoitureDto getVoitureById(String voitureId) {
		ModelMapper modelMapper =new ModelMapper();
		VoitureEntity voitureEntity=voitureRepository.findByVoitureId(voitureId);
		if(voitureEntity == null) throw new RuntimeException("car not found !!");
		VoitureDto voitureDto = modelMapper.map(voitureEntity, VoitureDto.class);
		return voitureDto;
	}

	@Override
	public VoitureDto updateVoiture(String id, VoitureDto voitureDto) {
		ModelMapper modelMapper = new ModelMapper();
		VoitureEntity voitureEntity =voitureRepository.findByVoitureId(id);
		if(voitureEntity==null) throw new RuntimeException("cette voiture n'existe pas !!");
		voitureEntity.setVoitureName(voitureDto.getVoitureName());
		voitureEntity.setMarque(voitureDto.getMarque());
		voitureEntity.setModele(voitureDto.getModele());
		voitureEntity.setAnneeModel(voitureDto.getAnneeModel());
		voitureEntity.setVille(voitureDto.getVille());
		voitureEntity.setPrix(voitureDto.getPrix());
		VoitureEntity updatedVoiture =voitureRepository.save(voitureEntity);
		VoitureDto updatedVoitureDto= modelMapper.map(updatedVoiture, VoitureDto.class);
		return updatedVoitureDto;
	}

	@Override
	public void deleteVoiture(String voitureId) {
		VoitureEntity voitureEntity =voitureRepository.findByVoitureId(voitureId);
		if(voitureEntity==null) throw new RuntimeException("car not found !!");
		voitureRepository.delete(voitureEntity);
		
	}

	@Override
	public List<VoitureDto> getAllVoitures(String email) {

		UserEntity currentUser =userRepository.findByEmail(email);
		List<VoitureEntity> voitures = currentUser.getAdmin()== true ? voitureRepository.findAll(): voitureRepository.findByUser(currentUser);
		Type  listTyp = new TypeToken<List<VoitureDto>>() {}.getType();
		List<VoitureDto> voituresDto = new ModelMapper().map(voitures, listTyp);
 
		return voituresDto;
	}

	@Override
	public List<VoitureDto> getAllVoitures() {
		List<VoitureEntity> voitures = voitureRepository.findAllVoitures();
		Type  listTyp = new TypeToken<List<VoitureDto>>() {}.getType();
		List<VoitureDto> voituresDto = new ModelMapper().map(voitures, listTyp);
 
		return voituresDto;
	}

	@Override
	public VoitureDto updatePhotoVoiture(String id, String voiturePhoto) {
		VoitureEntity checkVoiture = voitureRepository.findByVoitureId(id);
		checkVoiture.setVoiturePhoto(voiturePhoto);
		VoitureEntity voitureUpdated = voitureRepository.save(checkVoiture);
		ModelMapper modelMapper = new ModelMapper();
		VoitureDto voituretDtoUpdated = modelMapper.map(voitureUpdated, VoitureDto.class);
		return voituretDtoUpdated;
	}

}
