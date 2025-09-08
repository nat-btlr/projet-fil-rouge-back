package fr.filrougeback.controllers;

import fr.filrougeback.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	
	@Autowired
	VideoService videoService;

	/*
	@GetMapping("/public/home")
	public HomeDTO getHome() 
	{
		HomeDTO homeDTO = new HomeDTO();
		
		// Going through the list from BDD
		List<VideoCategory> categoryList = categoryService.list();
		// Transforming them in the list of categories DTO to communicate them to the Front
		List<CategoryDTO> listCategoryDTO = new ArrayList<CategoryDTO>();
		for (VideoCategory category : categoryList) {
			listCategoryDTO.add(new CategoryDTO(category));
		}
		
		homeDTO.setCategoryListDTO(null);
		
		return homeDTO;
	} */
}
