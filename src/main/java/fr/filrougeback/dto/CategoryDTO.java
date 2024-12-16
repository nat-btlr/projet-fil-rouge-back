package fr.filrougeback.dto;

import java.util.List;

import fr.filrougeback.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	
	String nameDTO;
	List<VideoDTO> videoListDTO;
	
	
	public CategoryDTO(Category category)
	{
		this.nameDTO = category.getNameCategory();
	}
}
