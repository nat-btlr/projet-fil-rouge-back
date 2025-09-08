package fr.filrougeback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.filrougeback.dto.RestAPIResponse;
import fr.filrougeback.dto.UpdateUserForm;
import fr.filrougeback.dto.UserDTO;
import fr.filrougeback.model.User;
import fr.filrougeback.service.UserService;

@RestController
public class UserRestController {

    @Autowired 
    private UserService userService;

    @PutMapping("/api/updateuser")
    public ResponseEntity<RestAPIResponse> updateUser(@RequestBody UpdateUserForm updateUserForm) {
        try {
            User existingUser = userService.findByEmail(updateUserForm.getCurrentEmail());
            
            if (existingUser == null) {
                return ResponseEntity.status(404).body(new RestAPIResponse(404, "User not found"));
            }

            User updatedUser = userService.updateUser(
                existingUser,
                updateUserForm.getNewUsername(),
                updateUserForm.getNewPassword(),
                updateUserForm.getNewEmail()
            );

            if (updatedUser != null) {
                return ResponseEntity.ok(new RestAPIResponse(200, "User updated successfully"));
            } else {
                return ResponseEntity.status(400).body(new RestAPIResponse(400, "No valid fields to update"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(new RestAPIResponse(500, "Internal Server Error"));
        }
    }
    
    @GetMapping("/api/getuser")
    public ResponseEntity<?> getUserInfo(@RequestParam(required = false) String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Parameter 'email' is missing or empty.");
        }
        try {
            User user = userService.findByEmail(email);
            if (user != null) {
                UserDTO userDTO = UserDTO.userFromEntity(user);
                return ResponseEntity.ok(userDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}
