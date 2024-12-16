package fr.filrougeback.service;

import fr.filrougeback.model.Like;
import fr.filrougeback.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Method for adding a like
    public boolean addLike(Long idUser, Long idVideo) {
        if (!likeRepository.existsByIdUserAndIdVideo(idUser, idVideo)) {
            likeRepository.save(new Like(idUser, idVideo));
            return true;
        }
        return false;
    }

    // Method for counting likes
    public long countLikes(Long idVideo) {
        return likeRepository.countByIdVideo(idVideo);
    }
}
