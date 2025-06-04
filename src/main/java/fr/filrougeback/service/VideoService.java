package fr.filrougeback.service;

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

				// Преобразуем строку из CSV в enum
				VideoCategory category;
				try {
					category = VideoCategory.valueOf(record.get("category").toUpperCase());
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(
							"Invalid category '" + record.get("category") + "'. Valid values: " +
									Arrays.toString(VideoCategory.values())
					);
				}

				// Creating an object Video and saving it in the DB
				Video video = new Video();
				video.setTitle(title);
				video.setDescription(description);
				video.setUrl(backBlazeService.getFileUrl(fileName));
				video.setCategory(category);

				videoRepository.save(video);
			}
		}
	}
}