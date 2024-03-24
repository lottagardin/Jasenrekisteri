package k24.jasenRekisteri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Asuinpaikka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paikkaId;
	
	private String asuinpaikka;

	public Asuinpaikka(Long id, String asuinpaikka) {
		this.paikkaId = id;
		this.asuinpaikka = asuinpaikka;
	}
	
	public Asuinpaikka () {}

	public Long getId() {
		return paikkaId;
	}

	public void setId(Long paikkaId) {
		this.paikkaId = paikkaId;
	}

	public String getAsuinpaikka() {
		return asuinpaikka;
	}

	public void setAsuinpaikka(String asuinpaikka) {
		this.asuinpaikka = asuinpaikka;
	}

	@Override
	public String toString() {
		return "Asuinpaikka [id=" + paikkaId + ", asuinpaikka=" + asuinpaikka + "]";
	}
	
	

}
