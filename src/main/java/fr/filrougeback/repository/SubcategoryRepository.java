package fr.filrougeback.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.filrougeback.model.Subcategory;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Integer> {
    
    public Subcategory findByNameSubcategory(String nameSubcategory);

    public List<Subcategory> findByCategoryIdCategory(Integer idCategory);
}
