package fr.filrougeback.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	int idVideo;
	
	@Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSubcategory", nullable = false)
    private Subcategory subcategory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes",
        joinColumns = @JoinColumn(name = "idVideo", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "idUser", nullable = false)
    )
    private Set<User> userLikes;
}
