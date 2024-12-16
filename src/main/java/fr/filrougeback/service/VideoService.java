package fr.filrougeback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.filrougeback.repository.VideoRepository;

@Service
public class VideoService {
	@Autowired
	VideoRepository videoRepository;

}
