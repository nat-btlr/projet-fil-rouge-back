package fr.filrougeback.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.filrougeback.dto.LikeDTO;
import fr.filrougeback.service.LikeService;

@RestController
@RequestMapping("api/likes")
public class LikeController {
	
	private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    
	@PostMapping
	public ResponseEntity<String> addLike(@RequestBody LikeDTO likeDTO) {
		boolean added = likeService.addLike(likeDTO.getUserId(), likeDTO.getVideoId());
		if (added) {
            return ResponseEntity.ok("Like added successfully");
        }
        return ResponseEntity.badRequest().body("User has already liked this video");
	}
	
	@GetMapping("/{videoId}")
	public ResponseEntity<Long> getLikeCount(@PathVariable Integer videoId) {
		long count = likeService.countLikes(videoId);
		return ResponseEntity.ok(count);
	}
}
