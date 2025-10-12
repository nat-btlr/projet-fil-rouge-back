package fr.filrougeback.dto;

import fr.filrougeback.model.Video;
import fr.filrougeback.model.VideoCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class VideoDTO {
    private int id;
    private String title;
    private String description;
    private String url;
    private VideoCategory category;

    // Tranforming one entity of type Video to VideoDTO
    public static VideoDTO fromEntity(Video video) {
        return new VideoDTO(
                video.getIdVideo(),
                video.getTitle(),
                video.getDescription(),
                video.getUrl(),
                video.getCategory()
        );
    }

    // Transforming list of Video into the list of VideoDTO
    public static List<VideoDTO> toDtoList(List<Video> videos) {
        return videos.stream()
                .map(VideoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}