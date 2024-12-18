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
    private int idLike;

    @Column(nullable = false)
    private int idUser;

    @Column(nullable = false)
    private int idVideo;

    // Constructors
    public Like(int idUser, int idVideo) {
        this.idUser = idUser;
        this.idVideo = idVideo;
    }
}
