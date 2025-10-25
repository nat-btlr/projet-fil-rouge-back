package fr.filrougeback.repository;

import org.springframework.data.repository.CrudRepository;

import fr.filrougeback.model.Like;

public interface LikeRepository extends CrudRepository<Like, Integer>{
	boolean existsByIdUserAndIdVideo(Integer idUser, Integer idVideo);
	long countByIdVideo(Integer idVideo);
	void deleteByIdUserAndIdVideo(Integer idUser, Integer idVideo);
}
