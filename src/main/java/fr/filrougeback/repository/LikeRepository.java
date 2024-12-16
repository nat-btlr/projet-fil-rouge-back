package fr.filrougeback.repository;

import org.springframework.data.repository.CrudRepository;

import fr.filrougeback.model.Like;

public interface LikeRepository extends CrudRepository<Like, Long>{
	boolean existsByIdUserAndIdVideo(Long idUser, Long idVideo);
    long countByIdVideo(Long idVideo);
}
