package k24.jasenRekisteri.model;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JasenRepository extends CrudRepository<Jasen, Long> {
	List<Jasen> findBySukunimi(String sukunimi);
	List<Jasen> findByAsuinpaikka(String asuinpaikka);
}
