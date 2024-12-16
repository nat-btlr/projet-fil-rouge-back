package fr.filrougeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationForm {
    private String email;
    private String password;
}
