package fr.filrougeback.service;

import fr.filrougeback.model.Like;
import fr.filrougeback.repository.LikeRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Method for adding a like
    public boolean addLike(Integer idUser, Integer idVideo) {
        if (!likeRepository.existsByIdUserAndIdVideo(idUser, idVideo)) {
            likeRepository.save(new Like(idUser, idVideo));
            return true;
        }
        return false;
    }

    // Method for counting likes
    public long countLikes(Integer idVideo) {
        return likeRepository.countByIdVideo(idVideo);
    }

    public boolean isLikedByUser(Integer idUser, Integer idVideo) {
    return likeRepository.existsByIdUserAndIdVideo(idUser, idVideo);
    }

    // Method for removing a like
    @Transactional
    public boolean removeLike(Integer idUser, Integer idVideo) {
        if (likeRepository.existsByIdUserAndIdVideo(idUser, idVideo)) {
            likeRepository.deleteByIdUserAndIdVideo(idUser, idVideo);
            return true;
        }
        return false;
    }
}
