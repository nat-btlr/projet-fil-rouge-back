package fr.filrougeback.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.filrougeback.service.CategoryService;
import fr.filrougeback.service.VideoService;
import fr.filrougeback.dto.CategoryDTO;
import fr.filrougeback.dto.HomeDTO;
import fr.filrougeback.model.Category;

@RestController
public class HomeRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	VideoService videoService;
	
	@GetMapping("/public/home")
	public HomeDTO getHome() 
	{
		HomeDTO homeDTO = new HomeDTO();
		
		// Going through the list from BDD
		List<Category> categoryList = categoryService.list();
		// Transforming them in the list of categories DTO to communicate them to the Front
		List<CategoryDTO> listCategoryDTO = new ArrayList<CategoryDTO>();
		for (Category category : categoryList) {
			listCategoryDTO.add(new CategoryDTO(category));
		}
		
		homeDTO.setCategoryListDTO(null);
		
		return homeDTO;
	}
}
