package fr.filrougeback.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private final String token;
    private final String username;
    private final String email;

    public AuthenticationResponse(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }
}
