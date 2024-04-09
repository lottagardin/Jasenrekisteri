package k24.jasenRekisteri.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Asuinpaikka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paikkaId;
	
	@JsonIgnore
	public String paikkanimi;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy =
			"asuinpaikka")
			private List<Jasen> jasenet;
	
	

	public List<Jasen> getJasenet() {
		return jasenet;
	}

	public void setJasenet(List<Jasen> jasenet) {
		this.jasenet = jasenet;
	}

	public Asuinpaikka(Long id, String paikkanimi) {
		this.paikkaId = id;
		this.paikkanimi = paikkanimi;
	}
	
	public Asuinpaikka () {}

	public Long getId() {
		return paikkaId;
	}

	public void setId(Long paikkaId) {
		this.paikkaId = paikkaId;
	}

	public String getPaikkaNimi() {
		return paikkanimi;
	}

	public void setPaikkaNimi(String paikkanimi) {
		this.paikkanimi = paikkanimi;
	}

	@Override
	public String toString() {
		return "Asuinpaikka [id=" + paikkaId + ", asuinpaikka=" + paikkanimi + "]";
	}
	
	

}
