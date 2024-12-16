package fr.filrougeback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.filrougeback.dto.CategoryDTO;
import fr.filrougeback.dto.RestAPIResponse;
import fr.filrougeback.service.CategoryService;

@RestController
public class AdminRestController {
	@Autowired
	CategoryService categoryService;
	
	/*
	@DeleteMapping("/api/category/delete")
	public Object deleteCategory(@PathVariable String idCategory) throws Exception {
		boolean ok = categoryService.deleteCategory(idCategory);
		if (ok)
			return ResponseEntity.ok(new RestAPIResponse(200, "category deleted"));
		else throw new Exception("Deletion problem");
	}*/

	@PostMapping("/api/category/add")
	public Object addCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
		System.out.println(categoryDTO.getNameDTO());
		if (!categoryService.exists(categoryDTO.getNameDTO())) {
			categoryService.createCategory(categoryDTO.getNameDTO());
			return ResponseEntity.ok(categoryDTO);
		} else throw new Exception("This category already exists");
	}
}
