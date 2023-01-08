package com.formation.mvc.services;

import java.util.List;

import com.formation.mvc.shared.dto.VoitureDto;


public interface VoitureService {
	
	   VoitureDto createVoiture(VoitureDto voitureDto, String email);
	   VoitureDto getVoitureById(String voitureId);
	   VoitureDto updateVoiture(String id, VoitureDto voitureDto);
	   public VoitureDto updatePhotoVoiture(String id,String voiturePhoto);
	   void deleteVoiture(String voitureId);
	   List<VoitureDto> getAllVoitures(String email);
	   List<VoitureDto> getAllVoitures();

}
