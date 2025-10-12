package fr.filrougeback.controllers;

import fr.filrougeback.dto.VideoDTO;
import fr.filrougeback.model.VideoCategory;
import fr.filrougeback.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class VideoConroller {
    @Autowired
    VideoService videoService;

    @GetMapping("/api/getvideos")
    public ResponseEntity<?> getVideos(@RequestParam String category) {
        try {
            VideoCategory videoCategory = VideoCategory.valueOf(category.toUpperCase());
            List<VideoDTO> videos = videoService.getVideosByCategory(videoCategory);

            if (videos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(videos);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid category. Available: " +
                    Arrays.toString(VideoCategory.values()));
        }
    }

    @GetMapping("api/search")
    public List<VideoDTO> search(@RequestParam String query) {
        return videoService.searchVideos(query);
    }
}
