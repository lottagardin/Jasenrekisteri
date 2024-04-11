package k24.jasenRekisteri.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import k24.jasenRekisteri.model.Asuinpaikka;
import k24.jasenRekisteri.model.AsuinpaikkaRepository;
import k24.jasenRekisteri.model.Jasen;
import k24.jasenRekisteri.model.JasenRepository;



@Controller
public class jasenController {


@Autowired
private JasenRepository jasenRepo; 
@Autowired
private AsuinpaikkaRepository asuinpaikkaRepo;

@GetMapping("/")
public String tyhja() {
	return "etusivu";
}
 

@GetMapping("/etusivu")
public String etusivu() {
	return "etusivu";
}

@GetMapping("/kirjaudu")
public String kirjaudu() {
	return "kirjaudu";
}

	
@GetMapping("/lomake")
public String lomake(Model model) {
	model.addAttribute("jasen", new Jasen());
	return "lomake";
}
	
@PostMapping("/lisaaJasen")
public String lisaaJasen(@Valid Jasen jasen, BindingResult bindingResult, Model model) {
	if (bindingResult.hasErrors()) {
		return "virheita";
	}

	String asuinpaikkanimi = jasen.getPaikkanimi();
	
	Asuinpaikka asuinpaikka = asuinpaikkaRepo.findByPaikkanimiIgnoreCase(asuinpaikkanimi);
	
	if (asuinpaikka == null) {
		Asuinpaikka uusipaikka = new Asuinpaikka ();
		uusipaikka.setPaikkaNimi(asuinpaikkanimi);
		asuinpaikkaRepo.save(uusipaikka);
		jasen.setAsuinpaikka(uusipaikka);
	} else if (asuinpaikka != null) {
		jasen.setAsuinpaikka(asuinpaikka);
	}
	jasenRepo.save(jasen);
	return "lisatty";
}

@GetMapping("/poista/{id}")
public String poistaJasen(@PathVariable("id") Long id) {
	jasenRepo.deleteById(id);
	return "jasenlista";
}

@GetMapping(value = "muokkaa/{id}")
public String muokkaaTietoja(@PathVariable("id") Long id, Model model) {

	Optional<Jasen> optionalJasen = jasenRepo.findById(id);
	Jasen jasen = optionalJasen.get();
	model.addAttribute("jasen", jasen);
	return "muutaJasen";
}

@GetMapping("/jasenlista")
public String jasenlista(Model model) {
	model.addAttribute("jasenet", jasenRepo.findAll());
	return "jasenlista";
}

@PostMapping("/tallennaMuutokset")
public String tallennaMuutokset(@Valid Jasen jasen, BindingResult bindingResult) {
	
	if (bindingResult.hasErrors()) {
		return "virheita";
	}
	
	long id = jasen.getId();
	
	Optional<Jasen> optJasen = jasenRepo.findById(id);
	Jasen muokattuJasen = optJasen.get();
	
	String asuinpaikkanimi = jasen.getPaikkanimi();
	Asuinpaikka olemassaolevapaikka = asuinpaikkaRepo.findByPaikkanimiIgnoreCase(asuinpaikkanimi);
	
	if (olemassaolevapaikka == null) {
		Asuinpaikka uusipaikka = new Asuinpaikka ();
		uusipaikka.setPaikkaNimi(asuinpaikkanimi);
		asuinpaikkaRepo.save(uusipaikka);
		muokattuJasen.setAsuinpaikka(uusipaikka);
	} else {
		muokattuJasen.setAsuinpaikka(olemassaolevapaikka);
	}
	
	
	muokattuJasen.setEtunimi(jasen.getEtunimi());
	muokattuJasen.setSukunimi(jasen.getSukunimi());
	muokattuJasen.setSposti(jasen.getSposti());

	
	jasenRepo.save(muokattuJasen);

	return"muokattu";
}



}




