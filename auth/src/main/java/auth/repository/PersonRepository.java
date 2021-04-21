package auth.repository;

import org.springframework.data.repository.CrudRepository;
import auth.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
