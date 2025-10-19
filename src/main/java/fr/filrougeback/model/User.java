package fr.filrougeback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
	//Creating the attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "email", nullable = false, unique = true)
	@NotNull(message = "The email is obligatory")
	String email;
	
	@Column(name="psw", nullable = false)
	@Size
	String password;
	
	@Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "The username is obligatory")
    String username;
	
	@Column(name = "gender", nullable = false)
    @NotNull(message = "The gender is obligatory")
    String gender;

	@Column(name = "role", nullable = false)
	@NotNull(message = "The role is obligatory")
	private String role;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "likes",
	    joinColumns = @JoinColumn(name = "idUser", nullable = false),
	    inverseJoinColumns = @JoinColumn(name = "idVideo", nullable = false)
	)
	private Set<Video> videoLikes;
}
	
	