package fr.filrougeback.service;

import fr.filrougeback.model.User;
import fr.filrougeback.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User createUser(String username, String password, String email, String gender) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setGender(gender);
        return userRepository.save(user);
    }

    public boolean exists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Transactional
    public User updateUser(User user, String newUsername, String newPassword, String newEmail) {
        if (user != null) {
        	System.out.println("User not null");
        	System.out.println(user);
        	System.out.println(user);
        	
            if (newUsername != null && !newUsername.isBlank()) {
                user.setUsername(newUsername);
                System.out.println("username updated" + newUsername);
            }
            if (newPassword != null && !newPassword.isBlank()) {
                user.setPassword(newPassword);
            }
            if (newEmail != null && !newEmail.isBlank()) {
                user.setEmail(newEmail);
            }

            return userRepository.save(user);
        } else {
        	System.out.println("User null");
        	return null;
        }
        
    }



}
