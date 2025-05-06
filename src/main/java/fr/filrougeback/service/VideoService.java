package fr.filrougeback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.filrougeback.model.Video;
import fr.filrougeback.repository.VideoRepository;

@Service
public class VideoService {
	@Autowired
	VideoRepository videoRepository;
	
	public List<Video> findAll() {
		List<Video> videos = videoRepository.findAll();
		return videos;
	}
	
	public Video addVideo(String title, String description, String url) {
		Video video = new Video();
		video.setTitle(title);
		video.setDescription(description);
		video.setUrl(url);
		return videoRepository.save(video);
	}
	
	public Video updateVideo(Video video, String newTitle, String newDescription, String newUrl) {
		if (video != null) {
			System.out.println("Video found");
			System.out.println(video);
			
            if (newTitle != null && !newTitle.isBlank()) {
                video.setTitle(newTitle);
                System.out.println("Title updated" + newTitle);
            }
            if (newDescription != null && !newDescription.isBlank()) {
                video.setDescription(newDescription);
            }
            if (newUrl != null && !newUrl.isBlank()) {
                video.setUrl(newUrl);
            }

            return videoRepository.save(video);
        } else {
        	System.out.println("Video null");
        	return null;
        }
	}
	
	public void deleteVideoByTitle(String Title) {
		
	}
}


