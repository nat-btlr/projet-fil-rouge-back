package fr.filrougeback.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscribeForm {
	
	private String email;
    private String password;
    private String username;
    private String gender;
}
