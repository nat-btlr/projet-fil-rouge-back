package fr.filrougeback.dto;

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
	public static UserDTO userFromEntity(User user) {
        return new UserDTO(user.getEmail(), user.getUsername());
    }
}
