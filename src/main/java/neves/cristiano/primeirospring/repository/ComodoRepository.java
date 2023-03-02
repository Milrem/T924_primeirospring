package neves.cristiano.primeirospring.repository;

import neves.cristiano.primeirospring.model.Comodo;
import org.springframework.data.repository.CrudRepository;

public interface ComodoRepository extends CrudRepository<Comodo, String> {
}
