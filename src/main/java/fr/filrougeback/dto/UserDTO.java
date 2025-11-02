package fr.filrougeback.dto;

import fr.filrougeback.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    private String username;
	public static UserDTO userFromEntity(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername());
    }
}
