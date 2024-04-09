package k24.jasenRekisteri.model;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AsuinpaikkaRepository extends CrudRepository<Asuinpaikka, Long> {

	Asuinpaikka findByPaikkanimiIgnoreCase(String paikkaNimi);


}
