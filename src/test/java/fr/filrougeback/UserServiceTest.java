package fr.filrougeback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.filrougeback.model.User;
import fr.filrougeback.model.Video;
import fr.filrougeback.repository.UserRepository;
import fr.filrougeback.service.UserService;

public class UserServiceTest {

@Test
public void testGetUserById() {
	when(userRepository.findByEmail("lili@lolo.fr"))
	.thenReturn(mockUser);

	User result = userService.get("lili@lolo.fr", "lisalisalisa");

	assertNotNull(result);
	assertEquals("lili@lolo.fr", result.getEmail());
	assertEquals("lisalisalisa", result.getPassword());

}

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User mockUser;
	
	@BeforeEach
	public void setUp() {
	Video mockVideo = new Video();
	Set<Video> videos = new HashSet<Video> ();
	videos.add(mockVideo);
	
	//ArrayList<Video> list = new ArrayList<Video> ();
	
	MockitoAnnotations.openMocks(this);
	mockUser = new User();
	
	mockUser.setEmail("lili@lolo.fr"); 
	mockUser.setId(0); 
	mockUser.setPassword("lisalisalisa"); 
	}
}