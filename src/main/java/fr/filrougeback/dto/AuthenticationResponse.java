package fr.filrougeback.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private final int id;
    private final String token;
    private final String username;
    private final String email;
    private final String role;

    public AuthenticationResponse(int id, String token, String username, String email, String role) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
