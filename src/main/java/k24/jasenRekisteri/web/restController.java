package k24.jasenRekisteri.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import k24.jasenRekisteri.model.Asuinpaikka;
import k24.jasenRekisteri.model.AsuinpaikkaRepository;
import k24.jasenRekisteri.model.Jasen;
import k24.jasenRekisteri.model.JasenRepository;
import k24.jasenRekisteri.model.Viesti;
 
@RestController
public class restController {
	

@Autowired
private JasenRepository jasenRepo; 
@Autowired
private AsuinpaikkaRepository asuinpaikkaRepo;


	@PostMapping("/lisaaJasenRest")
	public Viesti lisaaJasenRest(@Valid Jasen jasen, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return new Viesti("Lomakkeessa on virheitä, tarkista kentät");
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
		return new Viesti("jäsen lisätty onnistuneesti!");
	}
	
	@GetMapping("/poistaRest/{id}")
	public String poistaJasenRest(@PathVariable("id") Long id) {
		jasenRepo.deleteById(id);
		return "jäsen poistettu onnistuneesti!";
	}

	

@PostMapping("/tallennaMuutoksetRest")
public Viesti tallennaMuutoksetRest(@Valid Jasen jasen, BindingResult bindingResult) {
	
	if (bindingResult.hasErrors()) {
		return new Viesti("Lomakkeessa oli ongelmia, tarkista lomake");
	}
	
	String asuinpaikkanimi = jasen.getPaikkanimi();
	Asuinpaikka olemassaolevapaikka = asuinpaikkaRepo.findByPaikkanimiIgnoreCase(asuinpaikkanimi);
	
	if (olemassaolevapaikka == null) {
		Asuinpaikka uusipaikka = new Asuinpaikka ();
		uusipaikka.setPaikkaNimi(asuinpaikkanimi);
		asuinpaikkaRepo.save(uusipaikka);
	}
	
	long id = jasen.getId();
	
	Optional<Jasen> optJasen = jasenRepo.findById(id);
	Jasen muokattuJasen = optJasen.get();
	
	muokattuJasen.setEtunimi(jasen.getEtunimi());
	muokattuJasen.setSukunimi(jasen.getSukunimi());
	muokattuJasen.setSposti(jasen.getSposti());
	muokattuJasen.setAsuinpaikka(jasen.getAsuinpaikka());

	
	jasenRepo.save(muokattuJasen);

	return new Viesti ("Jäsen lisätty onnistuneesti!");
}

@GetMapping("/jasenlistaRest")
public @ResponseBody List<Jasen> jasenListaRest(){
	return (List<Jasen>)jasenRepo.findAll();
}


}
