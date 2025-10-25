package fr.filrougeback;

import fr.filrougeback.model.User;
import fr.filrougeback.repository.UserRepository;
import fr.filrougeback.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceTest {

@Test
public void testGetUserById() {
	// Мокаем userRepository
	when(userRepository.findByEmail("lili@lolo.fr"))
			.thenReturn(mockUser);
	// Мокаем passwordEncoder
	when(passwordEncoder.matches("lisalisalisa", mockUser.getPassword()))
			.thenReturn(true);

	User result = userService.get("lili@lolo.fr", "lisalisalisa");

	assertNotNull(result);
	assertEquals("lili@lolo.fr", result.getEmail());
}

	@Mock
	private UserRepository userRepository;

	@Mock
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	private User mockUser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockUser = new User();
		mockUser.setEmail("lili@lolo.fr");
		mockUser.setId(0);
		mockUser.setPassword("lisalisalisa"); // Здесь пароль не захеширован, но мы мокаем matches
	}
}