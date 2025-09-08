package fr.filrougeback.repository;

import fr.filrougeback.model.Video;
import fr.filrougeback.model.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
	void deleteById(Integer idVideo);

	List<Video> findByCategory(VideoCategory category);

	boolean existsByUrl(String url);
}