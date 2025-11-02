package fr.filrougeback.service;

import fr.filrougeback.model.User;
import fr.filrougeback.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;


    public User get(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }


    public User createUser(String username, String rawPassword, String email, String gender) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setUsername(username);
        user.setGender(gender);
        user.setRole("MEMBER");
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
            if (newUsername != null && !newUsername.isBlank()) {
                user.setUsername(newUsername);
            }
            if (newPassword != null && !newPassword.isBlank()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            if (newEmail != null && !newEmail.isBlank()) {
                user.setEmail(newEmail);
            }
            return userRepository.save(user);
        } else {
            return null;
        }
    }

        public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
