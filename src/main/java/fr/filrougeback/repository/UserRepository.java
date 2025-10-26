package fr.filrougeback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.filrougeback.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
    User findByUsername(String username);
    void deleteByEmail(String email);
    Iterable<User> findAll();

}
