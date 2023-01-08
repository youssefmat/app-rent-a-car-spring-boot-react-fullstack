package com.formation.mvc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formation.mvc.services.VoitureService;

@RestController
public class FileUploadController {
	@Autowired
	VoitureService voitureService;

	@PostMapping("/upload/{id}")
	public String uplodFile(MultipartFile file,@PathVariable String id ) {
		
		String destinationFileName = ".\\uploads\\"+file.getOriginalFilename();
		voitureService.updatePhotoVoiture(id,file.getOriginalFilename());
		
		try {
			
			Files.copy(file.getInputStream(), Paths.get(destinationFileName),StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			 throw new RuntimeException(e); 
		}
		
		return "file bien importer !";
	}
	
	@GetMapping(path="/dowloadFile/{fileName}", produces=MediaType.IMAGE_PNG_VALUE)
	public Resource dowloadFile(@PathVariable String fileName) {
		
		Path pathToFile = Paths.get(".\\uploads\\"+fileName);
		
		UrlResource resource =null;
		
		try {
			resource = new UrlResource(pathToFile.toUri());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return resource;
	}
}
