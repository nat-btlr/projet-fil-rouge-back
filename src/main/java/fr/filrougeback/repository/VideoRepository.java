package fr.filrougeback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.filrougeback.model.User;

@Repository
public interface VideoRepository extends CrudRepository<User, Integer>{

}
