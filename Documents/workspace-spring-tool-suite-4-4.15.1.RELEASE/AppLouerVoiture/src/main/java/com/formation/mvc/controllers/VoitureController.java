package com.formation.mvc.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import org.springframework.web.bind.annotation.RestController;

import com.formation.mvc.requests.VoitureRequest;
import com.formation.mvc.responses.VoitureResponse;
import com.formation.mvc.services.VoitureService;
import com.formation.mvc.shared.dto.VoitureDto;




@RestController
@RequestMapping("/voitures")
public class VoitureController {
	
	@Autowired
	VoitureService voitureService;
	
	// créer une voiture par un utilisateur 
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<VoitureResponse> addVoiture(@RequestBody VoitureRequest voitureRequest, Principal principal){
		
		ModelMapper modelMapper = new ModelMapper();
		VoitureDto voitureDto = modelMapper.map(voitureRequest, VoitureDto.class);
		VoitureDto createVoiture = voitureService.createVoiture(voitureDto, principal.getName());
		VoitureResponse newVoiture = modelMapper.map(createVoiture, VoitureResponse.class); 
		
		return new ResponseEntity<VoitureResponse>(newVoiture,HttpStatus.CREATED);
	}
	
	//recupérer tout les annonces des voitures
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<VoitureResponse>> getVoitures(Principal Principal){
		List<VoitureDto> voituresDto =voitureService.getAllVoitures(Principal.getName());
		Type listType = new TypeToken<List<VoitureResponse>>() {}.getType();
		List<VoitureResponse> voitureResponse = new ModelMapper().map(voituresDto, listType);
		
		return new ResponseEntity<List<VoitureResponse>>(voitureResponse,HttpStatus.OK);
	}
	// recupérer tout les annonces des voitures de maniére croissant par rapport le prix
	@GetMapping(path = "/anonnces",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<VoitureResponse>> getVoitures(){
		
		List<VoitureDto> voituresDto =voitureService.getAllVoitures();
		Type listType = new TypeToken<List<VoitureResponse>>() {}.getType();
		List<VoitureResponse> voitureResponse = new ModelMapper().map(voituresDto, listType);
		
		return new ResponseEntity<List<VoitureResponse>>(voitureResponse,HttpStatus.OK);
		
	}
	// modiffier une voiture
	@PutMapping(path = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			                   produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<VoitureResponse> updateVoiture(@PathVariable String id, @RequestBody VoitureRequest voitureRequest){
		
		ModelMapper modelMapper = new ModelMapper();
		VoitureDto voitureDto = modelMapper.map(voitureRequest, VoitureDto.class); 
		VoitureDto updatedVoiture =voitureService.updateVoiture(id, voitureDto);
		VoitureResponse  voitureResponse = modelMapper.map(updatedVoiture, VoitureResponse.class);
		return new ResponseEntity<VoitureResponse>(voitureResponse,HttpStatus.CREATED);
	}
	//récuperer une voiture par un id
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<VoitureResponse> getVoiture(@PathVariable(name = "id") String voitureId){
		ModelMapper modelMapper = new ModelMapper();
		VoitureDto voitureDto =voitureService.getVoitureById(voitureId);
		VoitureResponse voitureResponse =modelMapper.map(voitureDto, VoitureResponse.class);
		return new ResponseEntity<VoitureResponse>(voitureResponse,HttpStatus.OK);
	}
	// supprimer une voiture
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?>deleteVoiture(@PathVariable(name="id") String voitureId){
		voitureService.deleteVoiture(voitureId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
