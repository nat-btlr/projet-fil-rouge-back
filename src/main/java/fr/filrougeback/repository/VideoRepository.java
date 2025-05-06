package fr.filrougeback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.filrougeback.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{
	void deleteById(Integer idVideo);
}
