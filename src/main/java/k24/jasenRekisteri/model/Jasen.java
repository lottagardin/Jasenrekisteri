package k24.jasenRekisteri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Jasen {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@NotBlank
private String etunimi;
@NotBlank
private String sukunimi;
@NotBlank
@Email
private String sposti;

@NotBlank
private String paikkanimi;

@ManyToOne
@JoinColumn(name ="asuinpaikkaid")
private Asuinpaikka asuinpaikka;


public String getPaikkanimi() {
	return paikkanimi;
}
public void setPaikkanimi(String paikkanimi) {
	this.paikkanimi = paikkanimi;
}


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getEtunimi() {
	return etunimi;
}
public void setEtunimi(String etunimi) {
	this.etunimi = etunimi;
}
public String getSukunimi() {
	return sukunimi;
}
public void setSukunimi(String sukunimi) {
	this.sukunimi = sukunimi;
}
public String getSposti() {
	return sposti;
}
public void setSposti(String sposti) {
	this.sposti = sposti;
}

public Asuinpaikka getAsuinpaikka() {
	return asuinpaikka;
}
public void setAsuinpaikka(Asuinpaikka asuinpaikka) {
	this.asuinpaikka = asuinpaikka;
}

public Jasen(String etunimi, String sukunimi, String sposti, Asuinpaikka asuinpaikka) {
	this.etunimi = etunimi;
	this.sukunimi = sukunimi;
	this.sposti = sposti;
	this.asuinpaikka = asuinpaikka;
}

public Jasen() {}
@Override
public String toString() {
	return "Jasen [id=" + id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", sposti=" + sposti
			+ ", asuinpaikka=" + asuinpaikka + "]";
}





}
