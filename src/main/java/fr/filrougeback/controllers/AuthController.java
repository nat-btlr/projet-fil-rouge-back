package fr.filrougeback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.filrougeback.dto.AuthenticationForm;
import fr.filrougeback.dto.AuthenticationResponse;
import fr.filrougeback.dto.SubscribeForm;
import fr.filrougeback.dto.RestAPIResponse;
import fr.filrougeback.exceptions.UserDoesNotExistException;
import fr.filrougeback.model.User;
import fr.filrougeback.service.UserService;
import fr.filrougeback.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/public/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationForm loginRequest, 
                                        HttpServletResponse response) {
        User user = userService.get(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            String token = jwtService.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new AuthenticationResponse(user.getId(), token, user.getUsername(), user.getEmail(), user.getRole()));
        } else {
            throw new UserDoesNotExistException("Pas d'utilisateur avec cet email en base");
        }
    }
    
    @PostMapping(value="/public/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse> subscribe(@RequestBody SubscribeForm subscribeForm) throws Exception {
        try {
            if (!userService.exists(subscribeForm.getEmail())) {
                userService.createUser(
                	subscribeForm.getUsername(),
                    subscribeForm.getPassword(),
                    subscribeForm.getEmail(),
                    subscribeForm.getGender()
                );
                return ResponseEntity.ok(new RestAPIResponse(200, "The account was successfully created"));
            } else {
                throw new UserDoesNotExistException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(new RestAPIResponse(500, "Internal Server Error"));
        }
    }
    
    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
    	// Deleting the cookie for logout
        Cookie cookie = new Cookie("auth-token-vod", "");
        cookie.setMaxAge(0); // Deleting the cookie
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // Deleting cookie on the website
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
    
    
    @DeleteMapping("/api/deleteaccount")
    public ResponseEntity<RestAPIResponse> deleteAccount(@RequestParam String email) {
        try {
            User user = userService.findByEmail(email);
            if (user != null) {
                userService.deleteUserByEmail(email);
                return ResponseEntity.ok(new RestAPIResponse(200, "User account deleted successfully"));
            } else {
                return ResponseEntity.status(404).body(new RestAPIResponse(404, "User not found"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(new RestAPIResponse(500, "Internal Server Error"));
        }
    }


}