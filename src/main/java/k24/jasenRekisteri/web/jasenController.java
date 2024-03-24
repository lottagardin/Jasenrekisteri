package k24.jasenRekisteri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import k24.jasenRekisteri.model.AsuinpaikkaRepository;
import k24.jasenRekisteri.model.Jasen;
import k24.jasenRekisteri.model.JasenRepository;



@Controller
public class jasenController {


@Autowired
private JasenRepository jasenRepo; 
@Autowired
private AsuinpaikkaRepository asuinpaikkaRepo;


@GetMapping("/etusivu")
public String etusivu() {
	return "etusivu";
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
	jasenRepo.save(jasen);
	return "lisatty";
}

@PostMapping("/poista/{id}")
public String poistaJasen(@PathVariable("id") Long id, Model model) {
	jasenRepo.deleteById(id);
	return "jasenlista";
}


@GetMapping("/jasenlista")
public String jasenlista(Model model) {
	model.addAttribute("jasenet", jasenRepo.findAll());
	return "jasenlista";
}

@GetMapping("/etsi/{asuinpaikka}")
public String etsiAsuinpaikalla(@PathVariable("asuinpaikka") String asuinpaikka, Model model) {
	jasenRepo.findByAsuinpaikka(asuinpaikka);
	return "asuinpaikkahaku";
}

}
