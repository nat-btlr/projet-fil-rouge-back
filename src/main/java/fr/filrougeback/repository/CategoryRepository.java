package fr.filrougeback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.filrougeback.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Category findByNameCategory(String nameCategory);
    
}
