package fr.filrougeback.service;

import fr.filrougeback.dto.VideoDTO;
import fr.filrougeback.model.Video;
import fr.filrougeback.model.VideoCategory;
import fr.filrougeback.repository.VideoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private BackBlazeService backBlazeService;

	public void importStaticCsv() throws IOException {
		ClassPathResource resource = new ClassPathResource("/static/videos_metadata.csv");
		if (!resource.exists()) {
			throw new FileNotFoundException("CSV-file has not been found");
		}
		try (InputStream csvStream = resource.getInputStream()) {
			importVideosFromCsv(csvStream);
		}
	}

	public void importVideosFromCsv(InputStream csvFile) throws IOException {
		try (Reader reader = new InputStreamReader(csvFile);
			 CSVParser csvParser = new CSVParser(reader, CSVFormat.TDF.withFirstRecordAsHeader())) {

			for (CSVRecord record : csvParser) {
				String fileName = record.get("file_name");
				String title = record.get("title");
				String description = record.get("description");

				// Changing a category into ENUM
				VideoCategory category;
				try {
					category = VideoCategory.valueOf(record.get("category").toUpperCase());
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(
							"Invalid category '" + record.get("category") + "'. Valid values: " +
									Arrays.toString(VideoCategory.values())
					);
				}

				// Generating a URL using BackBlaze
				String url = backBlazeService.getFileUrl(fileName);

				// Checking if the video exists
				if (videoRepository.existsByUrl(url)) {
					continue;
				}

				// Creating and saving a new video
				Video video = new Video();
				video.setTitle(title);
				video.setDescription(description);
				video.setUrl(url);
				video.setCategory(category);

				videoRepository.save(video);
			}
		}
	}

	// Getting videos by category
	public List<VideoDTO> getVideosByCategory(VideoCategory category) {
		List<Video> videos = videoRepository.findByCategory(category);
		return VideoDTO.toDtoList(videos);
	}

	// Searching videos by keywords
	public List<VideoDTO> searchVideos(String keyword) {
		List<Video> videos = videoRepository.searchByKeyword(keyword);
		return VideoDTO.toDtoList(videos);
	}
}