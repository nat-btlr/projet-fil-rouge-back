package fr.filrougeback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idUser", "idVideo"})
})
public class Like {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;

    @Column(nullable = false)
    private Long idUser;

    @Column(nullable = false)
    private Long idVideo;

    // Constructors
    public Like(Long idUser, Long idVideo) {
        this.idUser = idUser;
        this.idVideo = idVideo;
    }
}
