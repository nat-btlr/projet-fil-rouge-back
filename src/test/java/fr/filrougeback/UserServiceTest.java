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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserServiceTest {
	@Test
	public void testCreateUser() {
		String username = "testuser";
		String rawPassword = "password";
		String email = "test@example.com";
		String gender = "female";

		when(passwordEncoder.encode(rawPassword)).thenReturn("encodedPassword");
		User userToSave = new User();
		userToSave.setEmail(email);
		userToSave.setPassword("encodedPassword");
		userToSave.setUsername(username);
		userToSave.setGender(gender);
		userToSave.setRole("MEMBER");

		when(userRepository.save(any(User.class))).thenReturn(userToSave);

		User created = userService.createUser(username, rawPassword, email, gender);
		assertNotNull(created);
		assertEquals(email, created.getEmail());
		assertEquals("encodedPassword", created.getPassword());
		assertEquals(username, created.getUsername());
		assertEquals(gender, created.getGender());
		assertEquals("MEMBER", created.getRole());
	}

	@Test
	public void testExists() {
		when(userRepository.findByEmail("exists@example.com")).thenReturn(mockUser);
		when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);

		assertTrue(userService.exists("exists@example.com"));
		assertFalse(userService.exists("notfound@example.com"));
	}

	@Test
	public void testDeleteUserByEmail() {
		userService.deleteUserByEmail("delete@example.com");
		verify(userRepository, times(1)).deleteByEmail("delete@example.com");
	}

	@Test
	public void testFindByEmail() {
		when(userRepository.findByEmail("find@example.com")).thenReturn(mockUser);
		User found = userService.findByEmail("find@example.com");
		assertNotNull(found);
		assertEquals(mockUser, found);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setUsername("oldName");
		user.setPassword("oldPassword");
		user.setEmail("old@example.com");

		when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		User updated = userService.updateUser(user, "newName", "newPassword", "new@example.com");
		assertNotNull(updated);
		assertEquals("newName", updated.getUsername());
		assertEquals("encodedNewPassword", updated.getPassword());
		assertEquals("new@example.com", updated.getEmail());
	}

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
		mockUser.setPassword("lisalisalisa");
	}
}