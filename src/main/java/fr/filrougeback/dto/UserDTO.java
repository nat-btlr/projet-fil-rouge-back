package fr.filrougeback.dto;

import java.util.List;

import fr.filrougeback.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String username;
    /*
	// For favorite categories
	List<CategoryDTO> categoryDTO;
	
	// For liked videos
	List<LikeDTO> likeDTO;
	*/
	public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getEmail(), user.getUsername());
    }
}
