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
public class Artisti {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long artisti_id;
	private String nimi;
	private String maa;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="artisti")
	//@JsonIgnore
	@JsonIgnoreProperties("artisti")  // one way to avoid infinite loop during JSON serialization/deserialization
	private List<Levy>levyt;

	public Artisti() {
		super();
	}

	public Artisti(String nimi, String maa) {
		super();
		this.nimi = nimi;
		this.maa=maa;
	}

	@Override
	public String toString() {
		return "Artisti [artisti_id=" + artisti_id + ", nimi=" + nimi + ", maa=" + maa +"]";
	}

	public long getArtisti_id() {
		return artisti_id;
	}

	public void setArtisti_id(long artisti_id) {
		this.artisti_id = artisti_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getMaa() {
		return maa;
	}

	public void setMaa(String maa) {
		this.maa = maa;
	}

	public List<Levy> getLevyt() {
		return levyt;
	}

	public void setLevyt(List<Levy> levyt) {
		this.levyt = levyt;
	}
	
	
}
