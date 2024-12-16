package fr.filrougeback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.filrougeback.model.Category;
import fr.filrougeback.model.User;
import fr.filrougeback.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> list(){
		return (List<Category>) categoryRepository.findAll();
	}
	
	public boolean exists(String _name) {
		return categoryRepository.findByNameCategory(_name) != null;
	}
	/*
	public boolean deleteCategory(String idCategory)
	{
		try {
			categoryRepository.delete(categoryRepository.findCategoryById(Integer.valueOf(idCategory)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}*/
	
	public Category createCategory(String _name) 
	{
		Category category = new Category();
		category.setNameCategory(_name);
		categoryRepository.save(category);
		return category;
	}
}
