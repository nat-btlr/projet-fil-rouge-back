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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
	//Creating the attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idUser;
	
	@Column(name = "email", nullable = false, unique = true)
	@NotNull(message = "The email is obligatory")
	String email;
	
	@Column(name="psw")
	@Size(min = 8, max = 20)
	String password;
	
	@Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "The username is obligatory")
    String username;
	
	@Column(name = "gender", nullable = false)
    @NotNull(message = "The gender is obligatory")
    String gender;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "likes",
	    joinColumns = @JoinColumn(name = "idUser", nullable = false),
	    inverseJoinColumns = @JoinColumn(name = "idVideo", nullable = false)
	)
	private Set<Video> videoLikes;
}
	
	