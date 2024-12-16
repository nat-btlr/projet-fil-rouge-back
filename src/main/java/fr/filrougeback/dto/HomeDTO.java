package fr.filrougeback.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeDTO {
	
	List<CategoryDTO> categoryListDTO;
	String name;
}
