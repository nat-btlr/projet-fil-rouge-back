package fr.filrougeback;

import fr.filrougeback.dto.VideoDTO;
import fr.filrougeback.model.Video;
import fr.filrougeback.model.VideoCategory;
import fr.filrougeback.repository.VideoRepository;
import fr.filrougeback.service.BackBlazeService;
import fr.filrougeback.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VideoServiceTest {

	@Mock
	private VideoRepository videoRepository;

	@Mock
	private BackBlazeService backBlazeService;

	@InjectMocks
	private VideoService videoService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testImportVideosFromCsv_validCsv_importsVideos() throws IOException {
		String csv = "file_name;title;description;category\nfile1.mp4;Title1;Desc1;HEALTH";
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		when(backBlazeService.getFileUrl("file1.mp4")).thenReturn("url1");
		when(videoRepository.existsByUrl("url1")).thenReturn(false);
		when(videoRepository.save(any(Video.class))).thenAnswer(invocation -> invocation.getArgument(0));

		videoService.importVideosFromCsv(inputStream);

		verify(videoRepository, times(1)).save(any(Video.class));
	}

	@Test
	public void testImportVideosFromCsv_invalidCategory_throwsException() {
		String csv = "file_name;title;description;category\nfile1.mp4;Title1;Desc1;INVALID";
		InputStream inputStream = new ByteArrayInputStream(csv.getBytes());
		when(backBlazeService.getFileUrl("file1.mp4")).thenReturn("url1");
		when(videoRepository.existsByUrl("url1")).thenReturn(false);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			videoService.importVideosFromCsv(inputStream);
		});
		assertTrue(exception.getMessage().contains("Invalid category"));
	}

	@Test
	public void testGetVideosByCategory_returnsDtoList() {
		Video video = new Video();
		video.setTitle("Title1");
		video.setCategory(VideoCategory.HEALTH);
		List<Video> videos = Collections.singletonList(video);
		when(videoRepository.findByCategory(VideoCategory.HEALTH)).thenReturn(videos);
		List<VideoDTO> dtos = videoService.getVideosByCategory(VideoCategory.HEALTH);
		assertEquals(1, dtos.size());
		assertEquals("Title1", dtos.get(0).getTitle());
	}

	@Test
	public void testSearchVideos_returnsDtoList() {
		Video video = new Video();
		video.setTitle("Title2");
		List<Video> videos = Arrays.asList(video);
		when(videoRepository.searchByKeyword("Title2")).thenReturn(videos);
		List<VideoDTO> dtos = videoService.searchVideos("Title2");
		assertEquals(1, dtos.size());
		assertEquals("Title2", dtos.get(0).getTitle());
	}
}
