package swd20.levykokoelma.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Formaatti {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long formaatti_id;
	private String nimi;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="formaatti")
	//@JsonIgnore
	@JsonIgnoreProperties("formaatti")  // one way to avoid infinite loop during JSON serialization/deserialization
	private List<Levy>levyt;

	public Formaatti() {
		super();
	}

	public Formaatti( String nimi) {
		super();
		this.nimi = nimi;

	}

	@Override
	public String toString() {
		return "Formaatti [formaatti_id=" + formaatti_id + ", nimi=" + nimi + "]";
	}

	public long getFormaatti_id() {
		return formaatti_id;
	}

	public void setFormaatti_id(long formaatti_id) {
		this.formaatti_id = formaatti_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Levy> getLevyt() {
		return levyt;
	}

	public void setLevyt(List<Levy> levyt) {
		this.levyt = levyt;
	}
	
	
}
