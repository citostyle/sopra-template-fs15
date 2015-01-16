package ch.uzh.ifi.seal.soprafs14.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.uzh.ifi.seal.soprafs14.model.Game;
import ch.uzh.ifi.seal.soprafs14.model.User;

@Repository("gameRepository")
public interface GameRepository extends CrudRepository<Game, Long> {
	User findByName(String name);
}
