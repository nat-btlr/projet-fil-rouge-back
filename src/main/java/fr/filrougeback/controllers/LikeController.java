package fr.filrougeback.controllers;

import fr.filrougeback.dto.LikeDTO;
import fr.filrougeback.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
	public ResponseEntity<?> getLikeCount(
		@PathVariable Integer videoId,
		@RequestParam(required = false) Integer userId
	) {
		long count = likeService.countLikes(videoId);
		boolean liked = false;
		if (userId != null) {
			liked = likeService.isLikedByUser(userId, videoId);
		}
		return ResponseEntity.ok(Map.of("count", count, "liked", liked));
	}

	@DeleteMapping
	public ResponseEntity<String> removeLike(@RequestBody LikeDTO likeDTO) {
    boolean removed = likeService.removeLike(likeDTO.getUserId(), likeDTO.getVideoId());
    if (removed) {
        return ResponseEntity.ok("Like removed successfully");
    }
    return ResponseEntity.badRequest().body("Like not found");
	}
}
